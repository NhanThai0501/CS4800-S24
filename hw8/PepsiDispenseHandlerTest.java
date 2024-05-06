import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PepsiDispenseHandlerTest {
    private PepsiDispenseHandler pepsiDispenseHandler;
    private VendingMachine vendingMachine;
    private Snack pepsi;

    @BeforeEach
    void setUp() {
        pepsiDispenseHandler = new PepsiDispenseHandler();
        vendingMachine = new VendingMachine();
        pepsi = new Snack("Pepsi", 1.5, 3);
    }

    @Test
    void dispenseWhenPepsiIsOutOfStock() {
        pepsi.setQuantity(0);
        vendingMachine.setSelectedSnack(pepsi);
        vendingMachine.setInsertedMoney(2.0);

        pepsiDispenseHandler.dispense(pepsi, vendingMachine);

        assertEquals(0, pepsi.getQuantity());
        assertEquals(2.0, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    void dispenseWhenDifferentSnackIsSelected() {
        Snack coke = new Snack("Coke", 1.5, 5);
        vendingMachine.setSelectedSnack(coke);
        vendingMachine.setInsertedMoney(2.0);

        pepsiDispenseHandler.dispense(coke, vendingMachine);

        assertEquals(5, coke.getQuantity());
        assertEquals(2.0, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }
}