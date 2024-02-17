/**
 * 
 */
package homework1_aggregation;

/**
 * @author NhanThai
 *
 */
public class Driver {
    public static void main(String[] args) {

        Course course = new Course();
        course.setCourseName("Software Development");

        Instructor instr1 = new Instructor();
        instr1.setFirstName("Nima");
        instr1.setLastName("Davarpanah");
        instr1.setOfficeNumber("3-2636");

        Textbook book1 = new Textbook();
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setPublisher("Prentice Hall");

        course.addInstructor(instr1);
        course.addTextbook(book1);

        Instructor instr2 = new Instructor();
        instr2.setFirstName("Edwin");
        instr2.setLastName("Rodriguez");
        instr2.setOfficeNumber("8-345");

        Textbook book2 = new Textbook();
        book2.setTitle("Design Patterns: Elements of Reusable Object-Oriented Software");
        book2.setAuthor("Erich Gamma, Richard Helm");
        book2.setPublisher("Addison-Wesley");

        course.addInstructor(instr2);
        course.addTextbook(book2);

        // Print course details
        course.details();
    }
}
