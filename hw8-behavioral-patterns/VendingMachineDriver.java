import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface State {
    void selectSnack(String snackName);
    void insertMoney(double amount);
    void dispenseSnack();
}

class IdleState implements State {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Snack selected: " + snackName);
        vendingMachine.setSelectedSnack(snackName);
        vendingMachine.setState(new WaitingForMoneyState(vendingMachine));
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Idle state: Please select a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Idle state: No snack selected.");
    }
}


class WaitingForMoneyState implements State {
    private VendingMachine vendingMachine;

    public WaitingForMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Waiting for money state: Snack already selected.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Money inserted: $" + amount);
        vendingMachine.addMoney(amount);
        if (vendingMachine.isEnoughMoney()) {
            System.out.println("Enough money inserted. Dispensing the snack.");
            vendingMachine.setState(new DispensingSnackState(vendingMachine));
        } else {
            System.out.println("Not enough money. Please insert more.");
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Waiting for money state: Insert money first.");
    }
}


class DispensingSnackState implements State {
    private VendingMachine vendingMachine;

    public DispensingSnackState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Dispensing state: Please wait, currently dispensing.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Dispensing state: Please wait, currently dispensing.");
    }

    @Override
    public void dispenseSnack() {
        vendingMachine.dispense();
        vendingMachine.reset();
        System.out.println("Returning to idle state.");
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}

class VendingMachine {
    private Map<String, Snack> snacks;
    private List<Snack> snackList;
    private State state;
    private String selectedSnack;
    private double moneyInserted;

    public VendingMachine() {
        snacks = new HashMap<>();
        snackList = new ArrayList<>();
        state = new IdleState(this);
        selectedSnack = "";
        moneyInserted = 0;
    }

    public void addSnack(Snack snack) {
        snacks.put(snack.getName().toLowerCase(), snack);
        snackList.add(snack);
    }

    public Snack getSnack(int index) {
        if (index >= 0 && index < snackList.size()) {
            return snackList.get(index);
        }
        return null;
    }

    public boolean isSnackAvailable(String snackName) {
        return snacks.containsKey(snackName.toLowerCase());
    }

    public java.util.Set<String> getSnackNames() {
        return snacks.keySet();
    }

    public Snack getSnack(String snackName) {
        return snacks.get(snackName.toLowerCase());
    }

    public List<Snack> getAllSnacks() {
        return snackList;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setSelectedSnack(String snackName) {
        selectedSnack = snackName.toLowerCase();
    }

    public void addMoney(double amount) {
        moneyInserted += amount;
    }

    public boolean isEnoughMoney() {
        Snack snack = snacks.get(selectedSnack);
        return snack != null && moneyInserted >= snack.getPrice();
    }

    public void dispense() {
        Snack snack = snacks.get(selectedSnack);
        if (snack != null && snack.getQuantity() > 0) {
            snack.decrementQuantity();
            System.out.println("Dispensing " + snack.getName());
        } else {
            System.out.println("Snack not available.");
        }
    }

    public void reset() {
        selectedSnack = "";
        moneyInserted = 0;
    }

    public void selectSnack(String snackName) {
        state.selectSnack(snackName);
    }

    public void insertMoney(double amount) {
        state.insertMoney(amount);
    }

    public void dispenseSnack() {
        state.dispenseSnack();
    }
}


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

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}

public class VendingMachineDriver {
    public static void main(String[] args) {
        // Creating snacks
        Snack coke = new Snack("Coke", 1.25, 5);
        Snack pepsi = new Snack("Pepsi", 1.25, 5);
        Snack cheetos = new Snack("Cheetos", 1.50, 3);
        Snack doritos = new Snack("Doritos", 1.50, 3);
        Snack kitkat = new Snack("KitKat", 1.00, 4);
        Snack snickers = new Snack("Snickers", 1.00, 2); // Quantity set to 2 to demonstrate depletion
    
        // Setting up the vending machine
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSnack(coke);
        vendingMachine.addSnack(pepsi);
        vendingMachine.addSnack(cheetos);
        vendingMachine.addSnack(doritos);
        vendingMachine.addSnack(kitkat);
        vendingMachine.addSnack(snickers);

        System.out.println("Welcome to the Vending Machine!");
    
        // Test dispensing snacks in order
        vendingMachine.selectSnack("Coke");
        System.out.println("Coke's quantity: " + coke.getQuantity());
        vendingMachine.insertMoney(1.25);
        vendingMachine.dispenseSnack();
        coke.decrementQuantity();
    
        System.out.println();
        vendingMachine.selectSnack("Pepsi");
        System.out.println("Pepsi's quantity: " + pepsi.getQuantity());
        vendingMachine.insertMoney(1.25);
        vendingMachine.dispenseSnack();
        pepsi.decrementQuantity();
    
        System.out.println();
        vendingMachine.selectSnack("Cheetos");
        System.out.println("Cheetos' quantity: " + cheetos.getQuantity());
        vendingMachine.insertMoney(1.50);
        vendingMachine.dispenseSnack();
        cheetos.decrementQuantity();
    
        System.out.println();
        vendingMachine.selectSnack("Doritos");
        System.out.println("Doritos' quantity: " + doritos.getQuantity());
        vendingMachine.insertMoney(1.50);
        vendingMachine.dispenseSnack();
        doritos.decrementQuantity();
    
        System.out.println();
        vendingMachine.selectSnack("KitKat");
        System.out.println("KitKat's quantity: " + kitkat.getQuantity());
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
        kitkat.decrementQuantity();
    
        // Test Snickers, the quantity should hit 0
        System.out.println();
        System.out.println("Testing Snickers: 3 purchases, should deplete stock.\n");

        vendingMachine.selectSnack("Snickers"); // First purchase
        System.out.println("Snickers' quantity: " + snickers.getQuantity());
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
    
        System.out.println();
        vendingMachine.selectSnack("Snickers"); // Second purchase should deplete
        System.out.println("Snickers' quantity: " + snickers.getQuantity());
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
    
        System.out.println();
        vendingMachine.selectSnack("Snickers"); // Third purchase, no stock
        System.out.println("Snickers' quantity: " + snickers.getQuantity());
        vendingMachine.insertMoney(1.00);
        vendingMachine.dispenseSnack();
    }
}









    