/**
 * 
 */
package homework1_inheritance;

/**
 * @author NhanThai
 *
 */
public class Employee {
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;

	// Constructor

	public Employee() {
		this.firstName = null;
		this.lastName = null;
		this.socialSecurityNumber = null;
    }

	public Employee(String fName, String lName, String ssn) {
		this.firstName = fName;
		this.lastName = lName;
		this.socialSecurityNumber = ssn;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String ssn) {
		this.socialSecurityNumber = ssn;
	}
	
    // Method to print employee details
    public void details() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("SSN: " + socialSecurityNumber);
    }

}
