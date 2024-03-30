import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorPatternDriverTest {
    static class CustomerOrderTests {

        private CustomerOrder order;

        @BeforeEach
        void setUp() {
            // Set up a new order for each test to ensure test isolation
            order = new CustomerOrder("Test Customer", LoyaltyStatus.GOLD);
        }

        @Test
        void calculateTotalCostWithNoItems() {
            assertEquals(0.0, order.calculateTotalCost(), "Total cost should be 0.0 for an empty order");
        }

        @Test
        void calculateTotalCostWithMultipleItemsAndDiscount() {
            order.addItem(new Burger()); // 5.99
            order.addItem(new Cheese(new Fries())); // 2.99 + 0.50 for cheese
            double expectedCost = (5.99 + 2.99 + 0.50) * (1 - LoyaltyStatus.GOLD.getDiscountRate()); // Applying GOLD discount
            assertEquals(expectedCost, order.calculateTotalCost(), 0.01, "Total cost calculation with discount is incorrect");
        }

        @Test
        void itemDescriptionIncludesToppings() {
            FoodItem customBurger = new Cheese(new Burger());
            order.addItem(customBurger);
            String expectedDescription = "Burger, Cheese";
            assertEquals(expectedDescription, customBurger.getDescription(), "Item description should include toppings");
        }

        // Implement additional tests for other methods and functionalities as needed
    }

    static class FoodItemAndToppingTests {

        @Test
        void pizzaBaseCostAndDescription() {
            FoodItem pizza = new Pizza();
            assertEquals(7.99, pizza.getCost(), "Pizza base cost should be 7.99");
            assertEquals("Pizza", pizza.getDescription(), "Description should be Pizza");
        }

        @Test
        void sandwichWithMultipleToppingsCostAndDescription() {
            FoodItem sandwich = new Sandwich();
            sandwich = new Cheese(sandwich);
            sandwich = new Bacon(sandwich);
            sandwich = new Avocado(sandwich);
            double expectedCost = 5.99 + 0.50 + 1.00 + 0.50; // Sandwich with Cheese, Bacon, and Avocado
            assertEquals(expectedCost, sandwich.getCost(), 0.01, "Sandwich with multiple toppings cost calculation is incorrect");
            assertTrue(sandwich.getDescription().contains("Cheese") && sandwich.getDescription().contains("Bacon") && sandwich.getDescription().contains("Avocado"), "Description should include all toppings");
        }

        @Test
        void addingSameToppingMultipleTimes() {
            FoodItem burger = new Burger();
            burger = new Cheese(burger); // First Cheese
            burger = new Cheese(burger); // Second Cheese
            double expectedCost = 5.99 + 0.50 + 0.50; // Burger with Cheese added twice
            assertEquals(expectedCost, burger.getCost(), 0.01, "Adding the same topping multiple times should accumulate cost");
            assertTrue(burger.getDescription().endsWith("Cheese, Cheese"), "Description should reflect multiple toppings of the same type");
        }

        @Test
        void dessertWithToppingNotTraditionallyUsed() {
            FoodItem dessert = new Dessert();
            dessert = new Ketchup(dessert); // Unusual but testing flexibility
            assertEquals(4.99 + 0.25, dessert.getCost(), 0.01, "Dessert with ketchup topping cost calculation is incorrect");
            assertEquals("Dessert, Ketchup", dessert.getDescription(), "Description should include ketchup, even if unusual");
        }

        @Test
        void drinkCostAndDescriptionTest() {
            FoodItem drink = new Drink();
            assertEquals(1.99, drink.getCost(), "Drink cost should be 1.99");
            assertEquals("Drink", drink.getDescription(), "Description should be Drink");
        }
    }
}


