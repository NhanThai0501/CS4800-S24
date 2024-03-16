import java.util.ArrayList;
import java.util.List;

public class PizzaChainDriver {
    // Inner Product class
    public static class Pizza {
        private List<String> toppings;
        private String size;
        private String chainName;

        public Pizza(String chainName, String size) {
            this.chainName = chainName;
            this.size = size;
            this.toppings = new ArrayList<>();
        }

        public void addTopping(String topping) {
            this.toppings.add(topping);
        }

        public void eat() {
            System.out.println("Pizza Chain: " + chainName);
            System.out.println("Size: " + size);
            System.out.println("Toppings: " + toppings + "\n");
        }
    }

    // Inner Concrete builder
    public static class PizzaBuilder {
        private Pizza pizza;

        public PizzaBuilder(String chainName) {
            this.pizza = new Pizza(chainName, "Not set");
        }

        public PizzaBuilder setSize(String size) {
            this.pizza.size = size;
            return this;
        }

        public PizzaBuilder addPepperoni() {
            pizza.addTopping("Pepperoni");
            return this;
        }

        public PizzaBuilder addSausage() {
            pizza.addTopping("Sausage");
            return this;
        }

        public PizzaBuilder addMushrooms() {
            pizza.addTopping("Mushrooms");
            return this;
        }

        public PizzaBuilder addBacon() {
            pizza.addTopping("Bacon");
            return this;
        }

        public PizzaBuilder addOnions() {
            pizza.addTopping("Onions");
            return this;
        }

        public PizzaBuilder addExtraCheese() {
            pizza.addTopping("Extra Cheese");
            return this;
        }

        public PizzaBuilder addPeppers() {
            pizza.addTopping("Peppers");
            return this;
        }

        public PizzaBuilder addChicken() {
            pizza.addTopping("Chicken");
            return this;
        }

        public PizzaBuilder addOlives() {
            pizza.addTopping("Olives");
            return this;
        }

        public PizzaBuilder addSpinach() {
            pizza.addTopping("Spinach");
            return this;
        }

        public PizzaBuilder addTomatoAndBasil() {
            pizza.addTopping("Tomato and Basil");
            return this;
        }

        public PizzaBuilder addBeef() {
            pizza.addTopping("Beef");
            return this;
        }

        public PizzaBuilder addHam() {
            pizza.addTopping("Ham");
            return this;
        }

        public PizzaBuilder addPesto() {
            pizza.addTopping("Pesto");
            return this;
        }

        public PizzaBuilder addSpicyPork() {
            pizza.addTopping("Spicy Pork");
            return this;
        }

        public PizzaBuilder addHamAndPineapple() {
            pizza.addTopping("Ham and Pineapple");
            return this;
        }

        public Pizza clear() {
            pizza = new Pizza(pizza.chainName, pizza.size);
            return pizza;
        }

        public Pizza build() {
            return pizza;
        }
    }

    // Inner Director
    public static class PizzaMaker {
        private PizzaBuilder pizzaBuilder;

        public PizzaMaker(PizzaBuilder pizzaBuilder) {
            this.pizzaBuilder = pizzaBuilder;
        }

        public Pizza makePizza() {
            return pizzaBuilder.build();
        }
    }

    // Main method
    public static void main(String[] args) {
        // Pizza Hut
        PizzaBuilder pizzaHutBuilder = new PizzaBuilder("Pizza Hut");
        pizzaHutBuilder.setSize("Large");
        pizzaHutBuilder.addPepperoni();
        pizzaHutBuilder.addSausage();
        pizzaHutBuilder.addMushrooms();

        Pizza pizzaHut1 = new PizzaMaker(pizzaHutBuilder).makePizza();
        pizzaHutBuilder.clear();

        pizzaHutBuilder.setSize("Medium");
        pizzaHutBuilder.addPepperoni();
        pizzaHutBuilder.addSausage();
        pizzaHutBuilder.addMushrooms();
        pizzaHutBuilder.addBacon();
        pizzaHutBuilder.addOnions();
        pizzaHutBuilder.addExtraCheese();
        Pizza pizzaHut2 = new PizzaMaker(pizzaHutBuilder).makePizza();
        pizzaHutBuilder.clear();

        pizzaHutBuilder.setSize("Small");
        pizzaHutBuilder.addPepperoni();
        pizzaHutBuilder.addSausage();
        pizzaHutBuilder.addMushrooms();
        pizzaHutBuilder.addBacon();
        pizzaHutBuilder.addOnions();
        pizzaHutBuilder.addExtraCheese();
        pizzaHutBuilder.addPeppers();
        pizzaHutBuilder.addChicken();
        pizzaHutBuilder.addOlives();
        Pizza pizzaHut3 = new PizzaMaker(pizzaHutBuilder).makePizza();
        pizzaHutBuilder.clear();

        pizzaHutBuilder.setSize("Large");
        pizzaHutBuilder.addPepperoni();
        pizzaHutBuilder.addSausage();
        pizzaHutBuilder.addMushrooms();
        Pizza pizzaHut4 = new PizzaMaker(pizzaHutBuilder).makePizza();
        pizzaHutBuilder.clear();

        pizzaHutBuilder.setSize("Small");
        pizzaHutBuilder.addPepperoni();
        pizzaHutBuilder.addSausage();
        Pizza pizzaHut5 = new PizzaMaker(pizzaHutBuilder).makePizza();

        // Little Caesars
        PizzaBuilder littleCaesarsBuilder = new PizzaBuilder("Little Caesars");
        littleCaesarsBuilder.setSize("Small");
        littleCaesarsBuilder.addPepperoni();
        littleCaesarsBuilder.addMushrooms();
        littleCaesarsBuilder.addBacon();
        littleCaesarsBuilder.addOnions();
        littleCaesarsBuilder.addExtraCheese();
        littleCaesarsBuilder.addPeppers();
        Pizza littleCaesars1 = new PizzaMaker(littleCaesarsBuilder).makePizza();
        littleCaesarsBuilder.clear();

        littleCaesarsBuilder.setSize("Medium");
        littleCaesarsBuilder.addPepperoni();
        littleCaesarsBuilder.addSausage();
        littleCaesarsBuilder.addMushrooms();
        littleCaesarsBuilder.addBacon();
        littleCaesarsBuilder.addOnions();
        littleCaesarsBuilder.addExtraCheese();
        littleCaesarsBuilder.addPeppers();
        littleCaesarsBuilder.addHamAndPineapple();
        Pizza littleCaesars2 = new PizzaMaker(littleCaesarsBuilder).makePizza();
        littleCaesarsBuilder.clear();

        littleCaesarsBuilder.setSize("Large");

        // Dominos
        PizzaBuilder dominosBuilder = new PizzaBuilder("Dominos");
        dominosBuilder.setSize("Small");
        dominosBuilder.addSausage();
        Pizza dominos1 = new PizzaMaker(dominosBuilder).makePizza();
        dominosBuilder.clear();

        dominosBuilder.setSize("Large");
        dominosBuilder.addBacon();
        dominosBuilder.addExtraCheese();
        dominosBuilder.addPeppers();
        Pizza dominos2 = new PizzaMaker(dominosBuilder).makePizza();
        dominosBuilder.clear();

        // Eating all pizzas
        System.out.println("Task 1: Pizza Hut only\n");
        pizzaHut1.eat();
        pizzaHut2.eat();
        pizzaHut3.eat();

        System.out.println("Task 2: All three chains: Pizza Hut, Little Caesars, Dominos\n");
        pizzaHut4.eat();
        pizzaHut5.eat();
        littleCaesars1.eat();
        littleCaesars2.eat();
        dominos1.eat();
        dominos2.eat();
    }
}
