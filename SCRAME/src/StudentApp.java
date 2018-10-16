import java.util.List;
import java.util.ArrayList;

public class StudentApp {
	
	private List<Student> studentList = new ArrayList<Student>();
	
	// GET METHOD
	
    /**
     * Retrieve the list of all students
     * @return student object list
     */
    public List<Student> getStudentList(){
        return studentList;
    }
    
    /**
     * Prints out the student IDs and student names
     */
    public void printStudentList(){
        System.out.printf(" ID\tStudent Name\n");
        for(int i = 0; i<studentList.size(); i++){
            System.out.printf("%6d\t%-30s\n", studentList.get(i).getStudentID(), studentList.get(i).getStudentName().toUpperCase());
        }
    }
    
    /**
     * Retrieve student information by student ID
     * @param student identification number
     * @return student object
     */
    public Student getStudentByID(int studentID) {
    	for (int i=0; i<studentList.size(); i++) {
    		Student tempStudent = studentList.get(i);
    		if (tempStudent.getStudentID() == studentID)
    			return tempStudent;
    	}
    	return null;
    }
    
    // SET METHOD
    
    /**
     * Add student information into the database
     * @param student identification number, student name
     */
    public void addStudent(int studentID, String studentName) {
    	studentList.add(new Student(studentID, studentName));
    }
    
    // CHEKCING METHOD
    
    /**
     * Check whether the given student identification number exists in the database
     * @param student identification number
     * @return true if studentID exists
     */
    public boolean isExistingStudentID(int studentID) {
    	for (int i=0; i<studentList.size(); i++) {
    		if (studentList.get(i).getStudentID() == studentID)
    			return true;
    	}
    	return false;
    }
}
