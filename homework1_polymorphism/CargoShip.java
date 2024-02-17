/**
 * 
 */
package homework1_polymorphism;

/**
 * @author NhanThai
 *
 */
public class CargoShip extends Ship {
    private int cargoCapacity;

    // No-argument constructor
    public CargoShip() {
        // The Ship class fields will be initialized to null, and cargoCapacity will be 0.
    }

    // Constructor with arguments
    public CargoShip(String name, String yearBuilt, int cargoCapacity) {
        super(name, yearBuilt);
        this.cargoCapacity = cargoCapacity;
    }

    // Setters and Getters
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    // Print function overridden
    @Override
    public void details() {
        // super.details(); // This line is commented out in your original code
        System.out.println("Cargo Ship Name: " + getName());
        System.out.println("Year Built: " + getYearBuilt());
        System.out.println("Cargo Capacity: " + cargoCapacity + " tonnage");
    }
}

