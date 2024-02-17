/**
 * 
 */
package homework1_inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author NhanThai
 *
 */
public class Driver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        List<Employee> employees = new ArrayList<>();

        SalariedEmployee se1 = new SalariedEmployee();
        se1.setFirstName("Joe");
        se1.setLastName("Jones");
        se1.setSocialSecurityNumber("111-11-1111");
        se1.setWeeklySalary(2500.00);
        employees.add(se1);

        HourlyEmployee he1 = new HourlyEmployee();
        he1.setFirstName("Stephanie");
        he1.setLastName("Smith");
        he1.setSocialSecurityNumber("222-22-2222");
        he1.setWage(25.00);
        he1.setHours(32);
        employees.add(he1);

        HourlyEmployee he2 = new HourlyEmployee();
        he2.setFirstName("Mary");
        he2.setLastName("Quinn");
        he2.setSocialSecurityNumber("333-33-3333");
        he2.setWage(19.00);
        he2.setHours(47);
        employees.add(he2);

        CommissionEmployee ce1 = new CommissionEmployee();
        ce1.setFirstName("Nicole");
        ce1.setLastName("Dior");
        ce1.setSocialSecurityNumber("444-44-4444");
        ce1.setGrossSales(50000.00);
        ce1.setCommissionRate(0.15);
        employees.add(ce1);

        SalariedEmployee se2 = new SalariedEmployee();
        se2.setFirstName("Renwa");
        se2.setLastName("Chanel");
        se2.setSocialSecurityNumber("555-55-5555");
        se2.setWeeklySalary(1700.00);
        employees.add(se2);

        BasePlusCommissionEmployee bce1 = new BasePlusCommissionEmployee();
        bce1.setFirstName("Mike");
        bce1.setLastName("Davenport");
        bce1.setSocialSecurityNumber("666-66-6666");
        bce1.setBaseSalary(95000.00);
        employees.add(bce1);

        CommissionEmployee ce2 = new CommissionEmployee();
        ce2.setFirstName("Mahnaz");
        ce2.setLastName("Vaziri");
        ce2.setSocialSecurityNumber("777-77-7777");
        ce2.setGrossSales(40000.00);
        ce2.setCommissionRate(0.22);
        employees.add(ce2);

        // Print out the details for each employee
        for (Employee emp : employees) {
            emp.details();
            System.out.println();
        }
    }

}
