import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {
    private VendingMachine vendingMachine;
    private Snack coke;
    private Snack pepsi;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
        coke = new Snack("Coke", 1.25, 5);
        pepsi = new Snack("Pepsi", 1.25, 5);

        vendingMachine.addSnack(coke);
        vendingMachine.addSnack(pepsi);
    }

    @Test
    public void testAddSnack() {
        assertEquals(2, vendingMachine.getAllSnacks().size(), "Should have 2 snacks");
    }

    @Test
    public void testGetSnackByIndex() {
        assertEquals(coke, vendingMachine.getSnack(0), "First snack should be Coke");
        assertEquals(pepsi, vendingMachine.getSnack(1), "Second snack should be Pepsi");
    }

    @Test
    public void testIsSnackAvailable() {
        assertTrue(vendingMachine.isSnackAvailable("Coke"), "Coke should be available");
        assertFalse(vendingMachine.isSnackAvailable("Sprite"), "Sprite should not be available");
    }

    @Test
    public void testIsEnoughMoney() {
        vendingMachine.setSelectedSnack("Coke");
        vendingMachine.addMoney(1.25);
        assertTrue(vendingMachine.isEnoughMoney(), "Should have enough money for Coke");

        vendingMachine.reset();
        vendingMachine.setSelectedSnack("Coke");
        vendingMachine.addMoney(1.00);
        assertFalse(vendingMachine.isEnoughMoney(), "Should not have enough money for Coke");
    }

    @Test
    public void testDispenseNotEnoughMoney() {
        vendingMachine.setSelectedSnack("Coke");
        vendingMachine.addMoney(1.00);
        vendingMachine.dispenseSnack();

        assertEquals(5, coke.getQuantity(), "Coke quantity should not decrease without enough money");
    }

    @Test
    public void testDispenseNotAvailable() {
        vendingMachine.setSelectedSnack("Sprite");
        vendingMachine.addMoney(1.50);
        vendingMachine.dispenseSnack();

        assertEquals(5, coke.getQuantity(), "Coke quantity should remain the same");
        assertEquals(5, pepsi.getQuantity(), "Pepsi quantity should remain the same");
    }
}
