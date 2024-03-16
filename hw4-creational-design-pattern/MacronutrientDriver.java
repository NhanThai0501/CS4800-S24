import java.util.*;

// Abstract Factory Class for Macronutrient Factories
abstract class AbstractFactory {
    abstract MacronutrientFactory getFactory(String macronutrientType);
}

// Singleton Abstract Factory Implementation
class MacronutrientAbstractFactory extends AbstractFactory {
    private static final MacronutrientAbstractFactory instance = new MacronutrientAbstractFactory();

    private MacronutrientAbstractFactory() {}

    public static MacronutrientAbstractFactory getInstance() {
        return instance;
    }

    @Override
    MacronutrientFactory getFactory(String macronutrientType) {
        switch (macronutrientType) {
            case "Carbs":
                return CarbsFactory.getInstance();
            case "Protein":
                return ProteinFactory.getInstance();
            case "Fats":
                return FatsFactory.getInstance();
            default:
                throw new IllegalArgumentException("Unknown factory type: " + macronutrientType);
        }
    }
}

// Macronutrient Factory Abstract Class
abstract class MacronutrientFactory {
    abstract String getRandomMacronutrient();
}

// Factory for creating Carbs (Singleton)
class CarbsFactory extends MacronutrientFactory {
    private static final CarbsFactory instance = new CarbsFactory();
    private static final List<String> options = Arrays.asList("Cheese", "Bread", "Lentils", "Pistachio");

    private CarbsFactory() {}

    public static CarbsFactory getInstance() {
        return instance;
    }

    @Override
    public String getRandomMacronutrient() {
        return options.get(new Random().nextInt(options.size()));
    }
}

// Similar singleton extensions for ProteinFactory and FatsFactory...
class ProteinFactory extends MacronutrientFactory {
    private static final ProteinFactory instance = new ProteinFactory();
    private static final List<String> options = Arrays.asList("Fish", "Chicken", "Beef", "Tofu");

    private ProteinFactory() {}

    public static ProteinFactory getInstance() {
        return instance;
    }

    @Override
    public String getRandomMacronutrient() {
        return options.get(new Random().nextInt(options.size()));
    }
}

class FatsFactory extends MacronutrientFactory {
    private static final FatsFactory instance = new FatsFactory();
    private static final List<String> options = Arrays.asList("Avocado", "Sour cream", "Tuna", "Peanuts");

    private FatsFactory() {}

    public static FatsFactory getInstance() {
        return instance;
    }

    @Override
    public String getRandomMacronutrient() {
        return options.get(new Random().nextInt(options.size()));
    }
} 

// Customer Information
class Customer {
    private String name;
    private String dietPlan;

    Customer(String name, String dietPlan) {
        this.name = name;
        this.dietPlan = dietPlan;
    }

    String getName() {
        return name;
    }

    String getDietPlan() {
        return dietPlan;
    }
}

// MealPlan abstract class
abstract class MealPlan {
    abstract String getCarb();
    abstract String getProtein();
    abstract String getFat();
}

// Specific MealPlans extend from MealPlan and utilize the factories...
class NoRestriction extends MealPlan {
    private MacronutrientFactory carbsFactory = CarbsFactory.getInstance();
    private MacronutrientFactory proteinFactory = ProteinFactory.getInstance();
    private MacronutrientFactory fatsFactory = FatsFactory.getInstance();

    @Override
    public String getCarb() {
        return carbsFactory.getRandomMacronutrient();
    }

    @Override
    public String getProtein() {
        return proteinFactory.getRandomMacronutrient();
    }

    @Override
    public String getFat() {
        return fatsFactory.getRandomMacronutrient();
    }
}

class Paleo extends MealPlan {
    private MacronutrientFactory carbsFactory = CarbsFactory.getInstance();
    private MacronutrientFactory proteinFactory = ProteinFactory.getInstance();
    private MacronutrientFactory fatsFactory = FatsFactory.getInstance();

    @Override
    public String getCarb() {
        String carb = carbsFactory.getRandomMacronutrient();
        while (!carb.equals("Pistachio")) {
            carb = carbsFactory.getRandomMacronutrient();
        }
        return carb;
    }

    @Override
    public String getProtein() {
        String protein = proteinFactory.getRandomMacronutrient();
        while (protein.equals("Tofu")) {
            protein = proteinFactory.getRandomMacronutrient();
        }
        return protein;
    }

    @Override
    public String getFat() {
        String fat = fatsFactory.getRandomMacronutrient();
        while (fat.equals("Cheese") || fat.equals("Sour cream")) {
            fat = fatsFactory.getRandomMacronutrient();
        }
        return fat;
    }
}

class Vegan extends MealPlan {
    private MacronutrientFactory carbsFactory = CarbsFactory.getInstance();
    private MacronutrientFactory proteinFactory = ProteinFactory.getInstance();
    private MacronutrientFactory fatsFactory = FatsFactory.getInstance();

    @Override
    public String getCarb() {
        String carb = carbsFactory.getRandomMacronutrient();
        while (carb.equals("Cheese")) {
            carb = carbsFactory.getRandomMacronutrient();
        }
        return carb;
    }

    @Override
    public String getProtein() {
        String protein = proteinFactory.getRandomMacronutrient();
        while (protein.equals("Fish") || protein.equals("Chicken") 
        || protein.equals("Tuna") || protein.equals("Beef")){
            protein = proteinFactory.getRandomMacronutrient();
        }
        return protein;
    }

    @Override
    public String getFat() {
        String fat = fatsFactory.getRandomMacronutrient();
        while (fat.equals("Cheese") || fat.equals("Sour cream")) {
            fat = fatsFactory.getRandomMacronutrient();
        }
        return fat;
    }
}

class NutAllergy extends MealPlan {
    private MacronutrientFactory carbsFactory = CarbsFactory.getInstance();
    private MacronutrientFactory proteinFactory = ProteinFactory.getInstance();
    private MacronutrientFactory fatsFactory = FatsFactory.getInstance();

    @Override
    public String getCarb() {
        String carb = carbsFactory.getRandomMacronutrient();
        while (carb.equals("Pistachio") || carb.equals("Peanuts")) {
            carb = carbsFactory.getRandomMacronutrient();
        }
        return carb;
    }

    @Override
    public String getProtein() {
        return proteinFactory.getRandomMacronutrient();
    }

    @Override
    public String getFat() {
        String fat = fatsFactory.getRandomMacronutrient();
        while (fat.equals("Pistachio") || fat.equals("Peanuts")) {
            fat = fatsFactory.getRandomMacronutrient();
        }
        return fat;
    }
}

// Factory Design Pattern for creating MealPlan instances
class MealPlanFactory {
    private static final MealPlanFactory instance = new MealPlanFactory();

    private MealPlanFactory() {}

    static MealPlanFactory getInstance() {
        return instance;
    }

    MealPlan createMealPlan(String dietPlan) {
        // Logic to return a specific MealPlan based on the diet plan

        // Example:
        switch (dietPlan) {
            case "No Restriction":
                return new NoRestriction();
            case "Paleo":
                return new Paleo();
            case "Vegan":
                return new Vegan();
            case "Nut Allergy":
                return new NutAllergy();
            default:
                throw new IllegalArgumentException("This is not a valid diet plan");
        }

    }
}

// Driver Program
public class MacronutrientDriver {
    public static void main(String[] args) {
        // Logic to interact with customers and MealPlanFactory
        List<Customer> customers = Arrays.asList(
            new Customer("John", "No Restriction"),
            new Customer("Emma", "Paleo"),
            new Customer("Michael", "Vegan"),
            new Customer("Sophia", "Nut Allergy"),
            new Customer("William", "Vegan"),
            new Customer("Olivia", "Paleo"),
            new Customer("James", "No Restriction"),
            new Customer("Emily", "Paleo"),
            new Customer("Alexander", "Vegan"),
            new Customer("Isabella", "Nut Allergy")
        );

        MealPlanFactory mealPlanFactory = MealPlanFactory.getInstance();

        for (Customer ctm : customers) {
            MealPlan mealPlan = mealPlanFactory.createMealPlan(ctm.getDietPlan());
            String carb = mealPlan.getCarb();
            String protein = mealPlan.getProtein();
            String fat = mealPlan.getFat();

            System.out.println("Customer #" + (customers.indexOf(ctm) + 1) + ": " + ctm.getName());
            System.out.println("Diet Plan: " + ctm.getDietPlan());
            System.out.println("Carb: " + carb);
            System.out.println("Protein: " + protein);
            System.out.println("Fat: " + fat);
            System.out.println();
        }
    }
}
