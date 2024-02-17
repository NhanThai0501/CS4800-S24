/**
 * 
 */
package homework1_polymorphism;

/**
 * @author NhanThai
 *
 */

public class Ship {
    private String name;
    private String yearBuilt;

    // No-argument constructor
    public Ship() {
        // Fields will be initialized to null
    }

    // Constructor with arguments
    public Ship(String name, String yearBuilt) {
        this.name = name;
        this.yearBuilt = yearBuilt;
    }

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    // Print function
    public void details() {
        System.out.println("Ship Name: " + name);
        System.out.println("Year Built: " + yearBuilt);
    }
}
