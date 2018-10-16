
public class Student {

    private String studentName;
    private int studentID;

    //CONSTRUCTOR

    /**
     * constructor for Students
     * @param studentName name of the student
     * @param studentID identification number of the student
     */
    public Student (int studentID, String studentName) {
        this.studentName = studentName;
        this.studentID = studentID;
    }

    //GET METHODS

    /**
     * Retrieves student identification number
     * @return studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Retrieves the student's name for this instance
     * @return name of the student
     */
    public String getStudentName() {
        return studentName;
    }

}