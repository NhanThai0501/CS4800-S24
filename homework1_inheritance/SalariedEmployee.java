/**
 * 
 */
package homework1_inheritance;

/**
 * @author NhanThai
 *
 */

public class SalariedEmployee extends Employee {
	private double weeklySalary;

	// Constructor
	public SalariedEmployee() {
		super();
		this.weeklySalary = 0;
	}
	
	public SalariedEmployee(String fName, String lName, String ssn, double weeklySalary) {
		super(fName, lName, ssn);
		this.weeklySalary = weeklySalary;
	}

	// Getter and Setter
	public double getWeeklySalary() {
		return weeklySalary;
	}

	public void setWeeklySalary(double weeklySalary) {
		this.weeklySalary = weeklySalary;
	}
	
    // Override printEmployeeDetails
    @Override
    public void details() {
        super.details(); // Call super to print base details
        System.out.println("Weekly Salary: $" + weeklySalary);
    }
}
