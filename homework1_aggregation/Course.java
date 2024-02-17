/**
 * 
 */
package homework1_aggregation;

/**
 * @author NhanThai
 *
 */
import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Instructor> instructors;
    private ArrayList<Textbook> textbooks;

    public Course() {
        instructors = new ArrayList<>();
        textbooks = new ArrayList<>();
    }

    public Course(String courseName) {
        this.courseName = courseName;
        this.instructors = new ArrayList<>();
        this.textbooks = new ArrayList<>();
    }

    // Getter and setters for adding course name:
    public void setCourseName(String cName) {
        this.courseName = cName;

    }

    public String getCourseName() {
        return courseName;
    }

    // Methods to add instructors and textbooks
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public void addTextbook(Textbook textbook) {
        textbooks.add(textbook);
    }

    public void details() {
        System.out.println("Course: " + courseName);
        for (Instructor instructor : instructors) {
            System.out.println("Instructor: " + instructor.getFirstName() + " " + instructor.getLastName()
                    + ", Office Number: " + instructor.getOfficeNumber());
        }
        for (Textbook textbook : textbooks) {
            System.out.println("Textbook: " + textbook.getTitle() + " by " + textbook.getAuthor() + ", Publisher: "
                    + textbook.getPublisher());
        }
    }
}
