/**
 * 
 */
package homework1_inheritance;

/**
 * @author NhanThai
 *
 */
public class CommissionEmployee extends Employee {
	private double grossSales;
	private double commissionRate;

	// Constructor
	public CommissionEmployee() {
		super();
		this.grossSales = 0;
		this.commissionRate = 0;
	}
	
	public CommissionEmployee(String fName, String lName, String ssn, double grossSales,
			double commissionRate) {
		super(fName, lName, ssn);
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}

	// Getters and Setters
	public double getGrossSales() {
		return grossSales;
	}

	public void setGrossSales(double grossSales) {
		this.grossSales = grossSales;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	@Override
    public void details() {
		super.details(); // Print base class details
        System.out.println("Commission rate: " + commissionRate);
        System.out.println("Gross sales: "  + grossSales);
    }
}
