import java.util.List;
import java.util.ArrayList;

/**
 * Manage course information
 */
public class CourseManager {
	
	private List<Course> courseList = new ArrayList<Course>();
	
	// GET METHODS
	
    /**
     * Retrieve the list of all courses
     * @return course object list
     */
	public List<Course> getCourseList(){
		return this.courseList;
	}
	
    /**
     * Prints out the course IDs and course names
     */
    public void printCourseList(){
        System.out.printf(" ID\tCourse Name\n");
        for(int i = 0; i<courseList.size(); i++){
            System.out.printf("%6d\t%-30s\n", courseList.get(i).getCourseID(), courseList.get(i).getCourseName().toUpperCase());
        }
    }
    
    /**
     * Retrieve course information by course ID
     * @param course identification number
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
     * Retrieves the capacity for all Sessions of a SessonOption type for a Course
     * @param courseID identification number of the course
     * @param option ENUM value of LECTURE, TUTORIAL, or LABORATORY
     * @return the number of capacity for the chosen session type
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
     * Retrieves the number of vacancies for all sessions of a SessionOption type for a course
     * @param courseID identification number of the course
     * @param option ENUM value of LECTURE, TUTORIAL, or LABORATORY
     * @return the number of vacancies for the chosen session type
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
     * Retrieves the number of courseworks of a Course
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
     * Retrieves the Exam Weight of a course
     * @param courseID Identification number of Course
     * @return Exam weightage of a given courseID
     */
    public double getExamWeightByCourseID(int courseID){
        //get Course object based on courseID
        Course course = getCourse(courseID);

        //return Exam Weight of Course
        return course.getExamWeight();
    }
}
