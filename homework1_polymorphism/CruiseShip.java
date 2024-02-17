/**
 * 
 */
package homework1_polymorphism;

/**
 * @author NhanThai
 *
 */
public class CruiseShip extends Ship {
    private int maxPassengers;

    // No-argument constructor
    public CruiseShip() {

    }

    // Constructor with arguments
    public CruiseShip(String name, String yearBuilt, int maxPassengers) {
        super(name, yearBuilt);
        this.maxPassengers = maxPassengers;
    }

    // Setters and Getters
    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    // Print function overridden
    @Override
    public void details() {
        // super.details(); // This line is commented out in your original code
        System.out.println("Cruise Ship Name: " + getName());
        System.out.println("Year Built: " + getYearBuilt());
        System.out.println("Maximum Passengers: " + maxPassengers);
    }
}
