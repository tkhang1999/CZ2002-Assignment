import java.util.List;
import java.util.ArrayList;
/*
 * Logic control of student information
 */
public class StudentManager {
	
	private List<Student> studentList = new ArrayList<Student>();
	
	// GET METHODS
	
    /**
     * Get the list of all students
     * @return list of student objects
     */
    public List<Student> getStudentList(){
        return studentList;
    }
    
    /**
     * Print out the student IDs and student names
     */
    public void printStudentList(){
        System.out.printf(" ID\tStudent Name\n");
        for(int i = 0; i<studentList.size(); i++){
            System.out.printf("%3d\t%-30s\n", studentList.get(i).getStudentID(), studentList.get(i).getStudentName().toUpperCase());
        }
    }
    
    /**
     * Get student information by student ID
     * @param studentID student identification number
     * @return student or null
     */
    public Student getStudent(int studentID) {
    	for (int i=0; i<studentList.size(); i++) {
    		Student student = studentList.get(i);
    		if (student.getStudentID() == studentID)
    			return student;
    	}
    	return null;
    }
    
    // SET METHOD
    
    /**
     * Add student information into the database
     * @param studentID student identification number
     * @param studentName student name
     */
    public void addStudent(int studentID, String studentName) {
    	studentList.add(new Student(studentID, studentName));
    }
    
    // CHEKCING METHOD
    
    /**
     * Check whether the given student identification number exists in the database
     * @param studentID student identification number
     * @return true if studentID exists
     */
    public boolean isExistingStudentID(int studentID) {
    	if (getStudent(studentID) != null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
