/*
 * Stores and retrieves student infomation
 */
public class Student {

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