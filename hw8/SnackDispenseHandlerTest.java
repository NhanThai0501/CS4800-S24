import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnackDispenseHandlerTest {
    @Test
    void setNextHandler() {
        SnackDispenseHandler handler1 = new CokeDispenseHandler();
        SnackDispenseHandler handler2 = new PepsiDispenseHandler();

        handler1.setNextHandler(handler2);

        assertEquals(handler2, handler1.nextHandler);
    }

    @Test
    void dispenseWhenSnackIsAvailable() {
        VendingMachine vendingMachine = new VendingMachine();
        Snack coke = new Snack("Coke", 1.5, 5);
        vendingMachine.setSelectedSnack(coke);
        vendingMachine.setInsertedMoney(2.0);

        SnackDispenseHandler cokeHandler = new CokeDispenseHandler();
        cokeHandler.dispense(coke, vendingMachine);

        assertEquals(4, coke.getQuantity());
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    void dispenseWhenSnackIsOutOfStock() {
        VendingMachine vendingMachine = new VendingMachine();
        Snack pepsi = new Snack("Pepsi", 1.5, 0);
        vendingMachine.setSelectedSnack(pepsi);
        vendingMachine.setInsertedMoney(2.0);

        SnackDispenseHandler pepsiHandler = new PepsiDispenseHandler();
        pepsiHandler.dispense(pepsi, vendingMachine);

        assertEquals(0, pepsi.getQuantity());
        assertEquals(2.0, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }

    @Test
    void dispenseWhenSnackIsNotFound() {
        VendingMachine vendingMachine = new VendingMachine();
        Snack chips = new Snack("Chips", 1.0, 3);
        vendingMachine.setSelectedSnack(chips);
        vendingMachine.setInsertedMoney(2.0);

        SnackDispenseHandler cokeHandler = new CokeDispenseHandler();
        SnackDispenseHandler pepsiHandler = new PepsiDispenseHandler();
        cokeHandler.setNextHandler(pepsiHandler);

        cokeHandler.dispense(chips, vendingMachine);

        assertEquals(3, chips.getQuantity());
        assertEquals(2.0, vendingMachine.getInsertedMoney());
        assertTrue(vendingMachine.getState() instanceof IdleState);
    }
}