import java.util.ArrayList;
import java.util.List;

// FoodItem interface
interface FoodItem {
    double getCost();
    String getDescription();
}

// Concrete FoodItem classes
class Burger implements FoodItem {
    @Override
    public double getCost() {
        return 5.99; // base price for a burger
    }

    @Override
    public String getDescription() {
        return "Burger";
    }
}

class Fries implements FoodItem {
    public double getCost() {
        return 2.99; // base price for fries
    }

    @Override
    public String getDescription() {
        return "Fries";
    }

}

class HotDog implements FoodItem {
    @Override
    public double getCost() {
        return 4.99; // base price for a hot dog
    }

    @Override
    public String getDescription() {
        return "Hot Dog";
    }
}

class Pizza implements FoodItem {
    @Override
    public double getCost() {
        return 7.99; // base price for a pizza
    }

    @Override
    public String getDescription() {
        return "Pizza";
    }
}

class ChickenWings implements FoodItem {
    @Override
    public double getCost() {
        return 6.99; // base price for chicken wings
    }

    @Override
    public String getDescription() {
        return "Chicken Wings";
    }
}

class Sandwich implements FoodItem {
    @Override
    public double getCost() {
        return 5.99; // base price for a sandwich
    }

    @Override
    public String getDescription() {
        return "Sandwich";
    }
}

class Soup implements FoodItem {
    @Override
    public double getCost() {
        return 3.99; // base price for a soup
    }

    @Override
    public String getDescription() {
        return "Soup";
    }
}

class Salad implements FoodItem {
    @Override
    public double getCost() {
        return 3.99; // base price for a salad
    }

    @Override
    public String getDescription() {
        return "Salad";
    }
}

class Dessert implements FoodItem {
    @Override
    public double getCost() {
        return 4.99; // base price for a dessert
    }

    @Override
    public String getDescription() {
        return "Dessert";
    }
}

class Drink implements FoodItem {
    @Override
    public double getCost() {
        return 1.99; // base price for a drink
    }

    @Override
    public String getDescription() {
        return "Drink";
    }

}


// ToppingDecorator abstract class
abstract class ToppingDecorator implements FoodItem {
    protected FoodItem item;

    public ToppingDecorator(FoodItem item) {
        this.item = item;
    }

    @Override
    public double getCost() {
        return item.getCost();
    }

    @Override
    public String getDescription() {
        return item.getDescription();
    }
}

// Concrete ToppingDecorator classes
class Cheese extends ToppingDecorator {
    public Cheese(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50; // Adding the cost of cheese
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese";
    }
}

class Bacon extends ToppingDecorator {
    public Bacon(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.00; // Adding the cost of bacon
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Bacon";
    }
}

class Ketchup extends ToppingDecorator {
    public Ketchup(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of ketchup
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Ketchup";
    }

}

class Mustard extends ToppingDecorator {
    public Mustard(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of mustard
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Mustard";
    }
}

class Lettuce extends ToppingDecorator {
    public Lettuce(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of lettuce
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Lettuce";
    }
}

class Tomato extends ToppingDecorator {
    public Tomato(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of tomato
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Tomato";
    }
}

class Onion extends ToppingDecorator {
    public Onion(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of onion
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Onion";
    }
}

class Pickles extends ToppingDecorator {
    public Pickles(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of pickles
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Pickles";
    }
}

class Jalapeno extends ToppingDecorator {
    public Jalapeno(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of jalapeno
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Jalapeno";
    }
}

class Avocado extends ToppingDecorator {
    public Avocado(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50; // Adding the cost of avocado
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Avocado";
    }
}

class Guacamole extends ToppingDecorator {
    public Guacamole(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50; // Adding the cost of guacamole
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Guacamole";
    }
}

class Salsa extends ToppingDecorator {
    public Salsa(FoodItem item) {
        super(item);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25; // Adding the cost of salsa
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Salsa";
    }
}

// CustomerOrder class
class CustomerOrder {
    private List<FoodItem> items;
    private String customerName;
    private LoyaltyStatus loyaltyStatus;

    public CustomerOrder(String customerName, LoyaltyStatus loyaltyStatus) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.loyaltyStatus = loyaltyStatus;
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public double calculateTotalCost() {
        double totalCost = items.stream().mapToDouble(FoodItem::getCost).sum();
        // Apply discount based on loyalty status
        return totalCost - (totalCost * loyaltyStatus.getDiscountRate());
    }

    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder();
        summary.append("\nCustomer: ").append(customerName).append("\n");
        summary.append("Food item(s) and Topping(s):\n");


        for (FoodItem item : items) {
            summary.append("- ").append(item.getDescription()).append(": $").append(String.format("%.2f", item.getCost())).append("\n");
        }
        // Total cost before discount
        summary.append("Total cost before ").append(loyaltyStatus).append(" discount: $").append(String.format("%.2f", items.stream().mapToDouble(FoodItem::getCost).sum())).append("\n");
        // Total cost after discount
        summary.append("Total cost after ").append(loyaltyStatus).append(" discount: $").append(String.format("%.2f", calculateTotalCost()));
        return summary.toString();
    }
}

// LoyaltyStatus enum
enum LoyaltyStatus {
    BRONZE(0.05), SILVER(0.10), GOLD(0.15), PLATINUM(0.20);

    public final double discountRate;

    LoyaltyStatus(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return name() + " (" + (int)(discountRate * 100) + "% discount)";
    }
}

// Main driver to run the program
public class DecoratorPatternDriver {
    public static void main(String[] args) {
        CustomerOrder order = new CustomerOrder("John", LoyaltyStatus.GOLD);

        FoodItem burger = new Burger();
        burger = new Cheese(burger); // Adding cheese topping
        burger = new Tomato(burger); // Adding tomato topping
        burger = new Onion(burger); // Adding onion topping
        burger = new Lettuce(burger); // Adding lettuce topping

        FoodItem hotDog = new HotDog();
        hotDog = new Ketchup(hotDog); // Adding ketchup topping
        hotDog = new Mustard(hotDog); // Adding mustard topping
        hotDog = new Pickles(hotDog); // Adding pickles topping
        hotDog = new Onion(hotDog); // Adding onion topping

        order.addItem(burger);
        order.addItem(hotDog);

        System.out.println(order.toString());

        CustomerOrder order2 = new CustomerOrder("Emma", LoyaltyStatus.SILVER);

        FoodItem fries = new Fries();
        fries = new Cheese(fries); // Adding cheese topping
        fries = new Ketchup(fries); // Adding ketchup topping
        fries = new Mustard(fries); // Adding mustard topping

        FoodItem dessert = new Dessert();
        dessert = new Cheese(dessert); // Adding cheese topping
        dessert = new Ketchup(dessert); // Adding ketchup topping
        dessert = new Mustard(dessert); // Adding mustard topping

        order2.addItem(fries);
        order2.addItem(dessert);

        System.out.println(order2.toString());

        CustomerOrder order3 = new CustomerOrder("Sophia", LoyaltyStatus.BRONZE);

        FoodItem pizza = new Pizza();
        pizza = new Cheese(pizza); // Adding cheese topping
        pizza = new Tomato(pizza); // Adding tomato topping
        pizza = new Onion(pizza); // Adding onion topping
        pizza = new Jalapeno(pizza); // Adding jalapeno topping

        FoodItem salad = new Salad();
        salad = new Cheese(salad); // Adding cheese topping
        salad = new Tomato(salad); // Adding tomato topping
        salad = new Onion(salad); // Adding onion topping
        salad = new Lettuce(salad); // Adding lettuce topping

        order3.addItem(pizza);
        order3.addItem(salad);

        System.out.println(order3.toString());

        CustomerOrder order4 = new CustomerOrder("Olivia", LoyaltyStatus.PLATINUM);

        FoodItem sandwich = new Sandwich();
        sandwich = new Cheese(sandwich); // Adding cheese topping
        sandwich = new Tomato(sandwich); // Adding tomato topping
        sandwich = new Onion(sandwich); // Adding onion topping
        sandwich = new Lettuce(sandwich); // Adding lettuce topping

        FoodItem soup = new Soup();
        soup = new Cheese(soup); // Adding cheese topping
        soup = new Tomato(soup); // Adding tomato topping
        soup = new Onion(soup); // Adding onion topping
        soup = new Jalapeno(soup); // Adding jalapeno topping

        order4.addItem(sandwich);
        order4.addItem(soup);

        System.out.println(order4.toString());
    }
}
