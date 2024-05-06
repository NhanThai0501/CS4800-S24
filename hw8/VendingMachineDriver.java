class Snack {
    private String name;
    private double price;
    private int quantity;

    public Snack(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

interface StateOfVendingMachine {
    void selectSnack(VendingMachine vendingMachine, Snack snack);
    void insertMoney(VendingMachine vendingMachine, double amount);
    void dispenseSnack(VendingMachine vendingMachine);
}

class IdleState implements StateOfVendingMachine {
    @Override
    public void selectSnack(VendingMachine vendingMachine, Snack snack) {
        vendingMachine.setSelectedSnack(snack);
        vendingMachine.setState(new WaitingForMoneyState());
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        System.out.println("Please select a snack first.");
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Please select a snack and insert money first.");
    }
}

class WaitingForMoneyState implements StateOfVendingMachine {
    @Override
    public void selectSnack(VendingMachine vendingMachine, Snack snack) {
        System.out.println("Snack already selected. Please insert money.");
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        vendingMachine.setInsertedMoney(amount);
        if (amount >= vendingMachine.getSelectedSnack().getPrice()) {
            vendingMachine.setState(new DispensingState());
            vendingMachine.dispenseSnack();
        } else {
            System.out.println("Insufficient money. Please insert more.");
        }
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        System.out.println("Please insert money first.");
    }
}

class DispensingState implements StateOfVendingMachine {
    @Override
    public void selectSnack(VendingMachine vendingMachine, Snack snack) {
        System.out.println("Snack is being dispensed. Please wait.");
    }

    @Override
    public void insertMoney(VendingMachine vendingMachine, double amount) {
        System.out.println("Snack is being dispensed. Please wait.");
    }

    @Override
    public void dispenseSnack(VendingMachine vendingMachine) {
        SnackDispenseHandler dispenseHandler = vendingMachine.getSnackDispenser();
        dispenseHandler.dispense(vendingMachine.getSelectedSnack(), vendingMachine);
    }
}


// SnackDispenseHandler.java
abstract class SnackDispenseHandler {
    protected SnackDispenseHandler nextHandler;

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void dispense(Snack snack, VendingMachine vendingMachine);
}
// ChocolateDispenseHandler.java
class ChocolateDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Chocolate")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing chocolate. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Chocolate is out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class CokeDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Coke")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing Coke. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Coke is out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class PepsiDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Pepsi")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing Pepsi. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Pepsi is out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class CheetosDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Cheetos")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing Cheetos. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Cheetos are out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class DoritosDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Doritos")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing Doritos. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Doritos are out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class KitKatDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("KitKat")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing KitKat. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("KitKat is out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

class SnickersDispenseHandler extends SnackDispenseHandler {
    @Override
    public void dispense(Snack snack, VendingMachine vendingMachine) {
        if (snack.getName().equals("Snickers")) {
            if (snack.getQuantity() > 0) {
                snack.setQuantity(snack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - snack.getPrice();
                System.out.println("Dispensing Snickers. Change: $" + change);
                vendingMachine.setState(new IdleState());
            } else {
                System.out.println("Snickers is out of stock.");
                vendingMachine.setState(new IdleState());
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(snack, vendingMachine);
        } else {
            System.out.println("Invalid snack selection.");
            vendingMachine.setState(new IdleState());
        }
    }
}

// VendingMachine.java
class VendingMachine {
    private StateOfVendingMachine state;
    private SnackDispenseHandler snackDispenser;
    private Snack selectedSnack;
    private double insertedMoney;

    public VendingMachine() {
        state = new IdleState();
        snackDispenser = setupSnackDispenser();
    }

    private SnackDispenseHandler setupSnackDispenser() {
        SnackDispenseHandler cokeHandler = new CokeDispenseHandler();
        SnackDispenseHandler pepsiHandler = new PepsiDispenseHandler();
        SnackDispenseHandler cheetosHandler = new CheetosDispenseHandler();
        SnackDispenseHandler doritosHandler = new DoritosDispenseHandler();
        SnackDispenseHandler kitkatHandler = new KitKatDispenseHandler();
        SnackDispenseHandler snickersHandler = new SnickersDispenseHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        return cokeHandler;
    }

    public void selectSnack(Snack snack) {
        state.selectSnack(this, snack);
    }

    public void insertMoney(double amount) {
        state.insertMoney(this, amount);
    }

    public void dispenseSnack() {
        state.dispenseSnack(this);
    }

    // Getters and setters
    public StateOfVendingMachine getState() {
        return state;
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    public SnackDispenseHandler getSnackDispenser() {
        return snackDispenser;
    }

    public void setSnackDispenser(SnackDispenseHandler snackDispenser) {
        this.snackDispenser = snackDispenser;
    }

    public Snack getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(Snack selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public void setInsertedMoney(double insertedMoney) {
        this.insertedMoney = insertedMoney;
    }
}

public class VendingMachineDriver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Create snacks
        Snack coke = new Snack("Coke", 1.5, 5);
        Snack pepsi = new Snack("Pepsi", 1.5, 3);
        Snack cheetos = new Snack("Cheetos", 1.0, 7);
        Snack doritos = new Snack("Doritos", 1.0, 4);
        Snack kitkat = new Snack("KitKat", 1.2, 6);
        Snack snickers = new Snack("Snickers", 1.2, 2);

        // Set up the Chain of Responsibility
        SnackDispenseHandler cokeHandler = new CokeDispenseHandler();
        SnackDispenseHandler pepsiHandler = new PepsiDispenseHandler();
        SnackDispenseHandler cheetosHandler = new CheetosDispenseHandler();
        SnackDispenseHandler doritosHandler = new DoritosDispenseHandler();
        SnackDispenseHandler kitkatHandler = new KitKatDispenseHandler();
        SnackDispenseHandler snickersHandler = new SnickersDispenseHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        vendingMachine.setSnackDispenser(cokeHandler);

        // Simulate user interactions
        System.out.println("Welcome to the Vending Machine!");
        System.out.println("Available snacks:");
        System.out.println("1. Coke ($1.5)");
        System.out.println("2. Pepsi ($1.5)");
        System.out.println("3. Cheetos ($1.0)");
        System.out.println("4. Doritos ($1.0)");
        System.out.println("5. KitKat ($1.2)");
        System.out.println("6. Snickers ($1.2)");
        System.out.println();

        System.out.println("Selecting Coke...");
        vendingMachine.selectSnack(coke);
        System.out.println("Inserting $2.0...");
        vendingMachine.insertMoney(2.0);

        System.out.println("\nSelecting Pepsi...");
        vendingMachine.selectSnack(pepsi);
        System.out.println("Inserting $1.5...");
        vendingMachine.insertMoney(1.5);

        System.out.println("\nSelecting Cheetos...");
        vendingMachine.selectSnack(cheetos);
        System.out.println("Inserting $3.0...");
        vendingMachine.insertMoney(3.0);

        System.out.println("\nSelecting Doritos...");
        vendingMachine.selectSnack(doritos);
        System.out.println("Inserting $3.0...");
        vendingMachine.insertMoney(3.0);

        System.out.println("\nSelecting KitKat...");
        vendingMachine.selectSnack(kitkat);
        System.out.println("Inserting $3.0...");
        vendingMachine.insertMoney(3.0);

        // Dispense Snickers until quantity hits 0
        System.out.println("\nSelecting Snickers...");
        vendingMachine.selectSnack(snickers);
        System.out.println("Inserting $2.0...");
        vendingMachine.insertMoney(2.0);

        System.out.println("\nSelecting Snickers...");
        vendingMachine.selectSnack(snickers);
        System.out.println("Inserting $2.0...");
        vendingMachine.insertMoney(2.0);

        // Try to dispense Snickers again when quantity is 0
        System.out.println("\nSelecting Snickers...");
        vendingMachine.selectSnack(snickers);
        System.out.println("Inserting $2.0...");
        vendingMachine.insertMoney(2.0);

        // Check quantities
        System.out.println("\nQuantities:");
        System.out.println("Coke: " + coke.getQuantity());
        System.out.println("Pepsi: " + pepsi.getQuantity());
        System.out.println("Cheetos: " + cheetos.getQuantity());
        System.out.println("Doritos: " + doritos.getQuantity());
        System.out.println("KitKat: " + kitkat.getQuantity());
        System.out.println("Snickers: " + snickers.getQuantity());
    }
}








    