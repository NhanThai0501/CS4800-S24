import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaitingForMoneyStateTest {
    private WaitingForMoneyState waitingForMoneyState;
    private VendingMachine vendingMachine;
    private Snack snack;

    @BeforeEach
    void setUp() {
        waitingForMoneyState = new WaitingForMoneyState();
        vendingMachine = new VendingMachine();
        snack = new Snack("Coke", 1.5, 5);
    }

    @Test
    void selectSnack() {
        vendingMachine.setSelectedSnack(snack);

        waitingForMoneyState.selectSnack(vendingMachine, snack);

        assertEquals(snack, vendingMachine.getSelectedSnack());
        assertTrue(vendingMachine.getState() instanceof WaitingForMoneyState);
    }

    @Test
    void insertMoneyWhenSufficientAmount() {
        vendingMachine.setSelectedSnack(snack);
        vendingMachine.setInsertedMoney(0.0);

        waitingForMoneyState.insertMoney(vendingMachine, 1.5);

        assertEquals(1.5, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof DispensingState);
    }

    @Test
    void insertMoneyWhenInsufficientAmount() {
        vendingMachine.setSelectedSnack(snack);
        vendingMachine.setInsertedMoney(0.0);

        waitingForMoneyState.insertMoney(vendingMachine, 1.0);

        assertEquals(1.0, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof WaitingForMoneyState);
    }

    @Test
    void dispenseSnack() {
        waitingForMoneyState.dispenseSnack(vendingMachine);

        assertTrue(vendingMachine.getState() instanceof WaitingForMoneyState);
    }
}