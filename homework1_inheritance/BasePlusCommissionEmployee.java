/**
 * 
 */
package homework1_inheritance;

/**
 * @author NhanThai
 *
 */
public class BasePlusCommissionEmployee extends Employee {
	private double baseSalary;

	// Constructor
	public BasePlusCommissionEmployee() {
		super();
		this.baseSalary = 0;
	}
	
	public BasePlusCommissionEmployee(String fName, String lName, String ssn, double baseSalary) {
		super(fName, lName, ssn);
		this.baseSalary = baseSalary;
	}

	// Getter and Setter
	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	@Override
    public void details() {
		super.details(); // Print base class details
        System.out.println("Base salary: " + baseSalary);
        
    }
}
