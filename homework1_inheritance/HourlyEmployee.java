/**
 * 
 */
package homework1_inheritance;

/**
 * @author NhanThai
 *
 */
public class HourlyEmployee extends Employee {
	private double wage;
	private double hours;

	// Constructor
	public HourlyEmployee() {
		super();
		this.wage = 0;
		this.hours = 0;
	}
	
	public HourlyEmployee(String fName, String lName, String ssn, double wage, double hours) {
		super(fName, lName, ssn);
		this.wage = wage;
		this.hours = hours;
	}

	// Getters and Setters
	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	@Override
    public void details() {
		super.details(); // Print base class details
        System.out.println("Wage: " + wage);
        System.out.println("Hours worked: " + hours);
    }
}
