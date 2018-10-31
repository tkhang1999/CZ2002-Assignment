import java.util.List;
import java.util.ArrayList;
/**
 * Logic control of course information
 */
public class CourseManager {
	
	private List<Course> courseList = new ArrayList<Course>();
	
	// GET METHODS
	
    /**
     * Get the list of all courses
     * @return list of course objects
     */
	public List<Course> getCourseList(){
		return this.courseList;
	}
	
    /**
     * prints a list of all courses with their respective ID, name and professors.
     */
    public void printCourseList() {
        //get list of all courses from courseDB
        List<Course> courseList = getCourseList();

        System.out.printf("%s\t%20s\t%s\n", "CourseID", "Course Name", "ProfessorID");

        for (int i = 0; i < courseList.size(); i++) {
            System.out.printf("%8d\t%20s\t%11d\n", courseList.get(i).getCourseID(), courseList.get(i).getCourseName(), courseList.get(i).getProfessorID());
        }
    }
    
    /**
     * Get the list of all CourseID
     * @return list of all CourseID
     */
    public int [] getCourseIDList() {
        //get list of all courses from courseDV
        List<Course> courseList = getCourseList();

        //new int Array to store courseID
        int[] courseIDList = new int[courseList.size()];

        //get all CourseIDs
        for (int i = 0; i < courseList.size(); i++)
            courseIDList[i] = courseList.get(i).getCourseID();
        return courseIDList;
    }
    
    /**
     * Get the course information by course ID
     * @param courseID identification number for the course
     * @return course object
     */
    public Course getCourse(int courseID) {
    	for (int i=0; i<courseList.size(); i++) {
    		Course course = courseList.get(i);
    		if (course.getCourseID() == courseID)
    			return course;
    	}
    	return null;
    }
    
    /**
     * Get the capacity for all Sessions of a SessonOption type for a Course
     * @param courseID identification number of the course
     * @param option ENUM value of LECTURE, TUTORIAL, or LABORATORY
     * @return the array of capacities for the chosen session type
     */
    public int [] getSessionCapacityByCourseID(int courseID, SessionOption option) {
        // Get Course object using courseID
        Course course = getCourse(courseID);

        // Choose which Session type to retrieve capacity
        switch (option) {
            case LECTURE:	// Lecture
                // Retrieve lectureList for Course
                List<Lecture> lectureList =  course.getLectureList();

                // int[] Array to store capacity
                int [] lectureCapacityArray = new int[lectureList.size()];

                // Capacity for all Lecture sessions
                for(int i = 0; i < lectureList.size(); i++)
                	lectureCapacityArray[i] = lectureList.get(i).getCapacity();
                return lectureCapacityArray;

            case TUTORIAL:	// Tutorial
                // Retrieve tutorialList for Course
                List<Tutorial> tutorialList =  course.getTutorialList();

                // int[] Array to store capacity
                int [] tutorialArray = new int[tutorialList.size()];

                // Capacity for all Tutorial sessions
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getCapacity();
                return tutorialArray;

            case LABORATORY:	// Laboratory
                // Retrieve LabList for Course
                List<Laboratory> laboratoryList =  course.getLaboratoryList();

                // int Array to store capacity
                int [] laboratoryArray= new int[laboratoryList.size()];

                // Capacity for all Laboratory sessions
                for(int i = 0; i < laboratoryList.size(); i++)
                    laboratoryArray[i] = laboratoryList.get(i).getCapacity();
                return laboratoryArray;
        }

        //if Course does not exist, return null
        return null;
    }
    
    /**
     * Get the number of vacancies for all sessions of a SessionOption type for a course
     * @param courseID identification number of the course
     * @param option ENUM value of LECTURE, TUTORIAL, or LABORATORY
     * @return the array of vacancies for the chosen session type
     */
    public int [] getSessionVacancyByCourseID(int courseID, SessionOption option) {
        // Get Course object based on courseID
        Course course = getCourse(courseID);

        // Choose which Session type to retrieve vacancy
        switch (option) {
            case LECTURE:   // Lecture
                // Retrieve lectureList for Course
                List<Lecture> lectureList =  course.getLectureList();

                // int[] Array to store vacancies
                int [] lectureArray = new int[lectureList.size()];

                // Vacancies for all Lecture sessions
                for(int i = 0; i < lectureList.size(); i++)
                    lectureArray[i] = lectureList.get(i).getVacancies();
                return lectureArray;

            case TUTORIAL:  // Tutorial
                // Retrieve tutorialList for Course
                List<Tutorial> tutorialList =  course.getTutorialList();

                // int[] Array to store vacancies
                int [] tutorialArray = new int[tutorialList.size()];

                // Vacancies for all Tutorial sessions
                for(int i = 0; i < tutorialList.size(); i++)
                    tutorialArray[i] = tutorialList.get(i).getVacancies();
                return tutorialArray;

            case LABORATORY:       // Laboratory
                // Retrieve laboraotryList for Course
                List<Laboratory> laboratoryList =  course.getLaboratoryList();

                // int[] Array to store vacancies
                int [] laboratoryArray= new int[laboratoryList.size()];

                // Vacancies for all Laboratory sessions
                for(int i = 0; i < laboratoryList.size(); i++)
                	laboratoryArray[i] = laboratoryList.get(i).getVacancies();
                return laboratoryArray;
        }

        //if Course does not exist, return null
        return null;
    }
    
    /**
     * Get the number of courseworks of a Course
     * @param courseID Identification number of the course
     * @return number of courseworks of a course
     */
    public int getNumComponentsByCourseID(int courseID){
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //return number of courseworks of a Course
        return course.getNumCoursework();
    }
    
    /**
     * Get all coursework weightage of a particular course
     * @param courseID course identification number
     * @return courseworkWeight of all courseworks of a course in a double Array
     */
    public double[] getCourseworkWeightByCourseID(int courseID){
        // Get Course object based on courseID
        Course course = getCourse(courseID);

        // Return all coursework weight of Course
        return course.getCourseworkWeight();
    }
    
    /**
     * Get the Exam Weight of a course
     * @param courseID Identification number of Course
     * @return Exam weightage of a given courseID
     */
    public double getExamWeightByCourseID(int courseID){
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //return Exam Weight of Course
        return course.getExamWeight();
    }
    
    // SET METHODS
    
    /**
     * Add Course into courseDB after instantiating Course
     * @param courseID identification number of course
     * @param courseName name of course
     * @param professorID identification number of professor
     * @param capacityLecture maximum number of students this lecture can take
     * @param capacityTutorial maximum number of students this tutorial can take
     * @param capacityLab maximum number of students this lab can take
     */
    public void addCourse (int courseID, String courseName, int professorID, int [] capacityLecture, int [] capacityTutorial, int [] capacityLab) {
        //create new Course object
        Course course = new Course(courseID, courseName,  professorID);

        //add Lectures, add Tutorials and add Labs for new Course
        course.addLecture(capacityLecture);
        course.addTutorial(capacityTutorial);
        course.addLaboratory(capacityLab);

        //pass Course object to courseDB to be added
        courseList.add(course);
    }
    
    /**
     * Set vacancy for a Session of a Course
     * @param courseID identification number of course
     * @param option ENUM value of LECTURE, LAB, TUTORIAL. Represents the difference Session types.
     * @param ID identification number of the chosen lecture,lab or tutorial
     */
    public void setVacancyByCourseSession(int courseID, SessionOption option, int ID) {

        //get Course object based on courseID
        Course course = getCourse(courseID);

        //Choose which Session type to set vacancy
        switch (option) {
            case LECTURE:   //Lecture
                course.setLectureVacancy(ID);
            break;

            case TUTORIAL:  //Tutorial
                course.setTutorialVacancy(ID);
            break;

            case LABORATORY:       //Laboratory
                course.setLaboratoryVacancy(ID);
            break;
        }
    }
    
    /**
     * Set the component weightage using courseID
     * @param courseID identification number of the course to set
     * @param examWeight exam weightage of the course to set
     * @param courseworkWeight coursework weightage of the course to set
     */
    public void setComponentWeightByCourseID(int courseID, double examWeight, double [] courseworkWeight) {
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //setComponentWeight for Course
        course.setComponentWeightage(examWeight, courseworkWeight);
    }
    
    /**
     * Write the list of courses to a .dat file
     */
    public void writetCourseList() {
    	SerializeDB.writeSerializedObject("course.dat", courseList);
    }
    
    /**
     * Read a the list of courses from a .dat file
     */
    public void readCourseList() {
    	courseList = (ArrayList<Course>)SerializeDB.readSerializedObject("course.dat");
    }
    
    // CHECKING METHODS
    
    /**
     * Check whether Course exist in CourseDB
     * @param courseID identification number of the course to check
     * @return true if course already exists in database
     */
    public boolean isExistingCourse(int courseID) {
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //if Course does not exist
        if ( course == null)
            return false;

        //if courseID exists
        return true;
    }

    /**
     * Check whether Course is ready for registration
     * @param courseID identification number of the course to check
     * @return true if course is ready for registration
     */
    public boolean isCourseReadyForRegistrationByID(int courseID) {
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //if courseID exist AND courseComponents valid
        if (isExistingCourse(courseID) && course.isCourseValid())
            return true;

        return false;
    }
}
