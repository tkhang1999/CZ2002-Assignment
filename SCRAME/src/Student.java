import java.io.Serializable;
/**
 * Store and get student information
 */
public class Student implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -279676679609314864L;
	private String studentName;
    private int studentID;

    //CONSTRUCTOR

    /**
     * Constructor for Student
     * @param studentName name of the student
     * @param studentID identification number of the student
     */
    public Student (int studentID, String studentName) {
        this.studentName = studentName;
        this.studentID = studentID;
    }

    //GET METHODS

    /**
     * Get student identification number
     * @return studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Get the student's name for this instance
     * @return name of the student
     */
    public String getStudentName() {
        return studentName;
    }

}