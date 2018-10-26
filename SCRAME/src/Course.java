import java.util.ArrayList;
import java.util.List;

/**
 * Store and get course information
 */
public class Course {

	private int courseID;
	private String courseName;
	private int professorID;
	
	private double examWeight;
	private double[] courseworkWeight;
	private int numCoursework;

	private List<Lecture> lectureList = new ArrayList<Lecture>();
	private List<Tutorial> tutorialList = new ArrayList<Tutorial>();
	private List<Laboratory> laboratoryList = new ArrayList<Laboratory>();

	/**
	 * Constructor for Course
	 * @param courseID      identification number of the course
	 * @param courseName    name of the course
	 * @param professorID   identification number of the professor
	 */

	public Course(int courseID, String courseName, int professorID) {

		this.courseName = courseName;
		this.courseID = courseID;
		this.professorID = professorID;
	}

	// GET METHODS

	/**
	 * Get the course ID
	 * @return courseID
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * Get the course name
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Get the professor ID
	 * @return professor ID
	 */
	public int getProfessorID() {
		return professorID;
	}

	/**
	 * Get the number of courseworks for a particular course
	 * @return number of courseworks for a particular course
	 */
	public int getNumCoursework() {
		return numCoursework;
	}

	/**
	 * Get the weightage of the exam component of a particular course
	 * @return weightage of exam for a particular course
	 */
	public double getExamWeight() {
		return examWeight;
	}

	/**
	 * Get the weightage of all the coursework components in the course
	 * @return weightage of coursework
	 */
	public double[] getCourseworkWeight() {

		return courseworkWeight;
	}

	/**
	 * Get the list of lectures of a particular course
	 * @return list of lectures
	 */
	public List<Lecture> getLectureList() {
		return this.lectureList;
	}

	/**
	 * Get the list of tutorials of a particular course
	 * @return list of tutorials
	 */
	public List<Tutorial> getTutorialList() {
		return this.tutorialList;
	}

	/**
	 * Get the list of labs of a particular course
	 * @return list of labs
	 */
	public List<Laboratory> getLaboratoryList() {
		return this.laboratoryList;
	}

	// SET METHODS

	/**
	 * Set the component weightage set method for courseManager to call upon to set
	 * the weightage of individual components in the course
	 * @param examWeight       weightage of exam
	 * @param courseworkWeight weightage of coursework
	 */
	public void setComponentWeightage(double examWeight, double[] courseworkWeight) {
		this.examWeight = examWeight;
		this.courseworkWeight = courseworkWeight;
		this.numCoursework = courseworkWeight.length;
	}

	/**
	 * Add lectures into the course
	 * @param capacity array that stores the number of students allowed to take each
	 *                 particular lecture
	 */
	public void addLecture(int[] capacity) {

		// initializing i to 0 so that it can be used to cycle through the capacity
		// array to know how many lectures to be added
		int i = 0;

		// cycling through the capacity array to know the capacities of the individual
		// lecture and then adding it into the particular lecture with ID i
		while (i < capacity.length) {

			// instantiating a new lecture and adding it into the lecture list
			Lecture newLecture = new Lecture(i, capacity[i]);
			lectureList.add(newLecture);
			i++;
		}
	}

	/**
	 * Add tutorials into the course
	 * @param capacity array that stores the number of students allowed to take each
	 *                 particular tutorial
	 */
	public void addTutorial(int[] capacity) {

		// initializing i to 0 so that it can be used to cycle through the capacity
		// array to know how many tutorials to be added
		int i = 0;

		// cycling through the capacity array to know the capacities of the individual
		// tutorials and then adding it into the particular tutorial with ID i
		while (i < capacity.length) {

			// instantiating a new tutorial and adding it into the tutorial list
			Tutorial newTutorial = new Tutorial(i, capacity[i]);
			tutorialList.add(newTutorial);
			i++;
		}
	}

	/**
	 * Add laboratories into the course
	 * @param capacity array that stores the number of students allowed to take each
	 *                 particular lab
	 */
	public void addLaboratory(int[] capacity) {

		// initializing i to 0 so that it can be used to cycle through the capacity
		// array to know how many labs to be added
		int i = 0;

		// cycling through the capacity array to know the capacities of the individual
		// labs and then adding it into the particular lab with ID i
		while (i < capacity.length) {

			// instantiating a new lab and adding it into the lab list
			Laboratory newLaboratory = new Laboratory(i, capacity[i]);
			laboratoryList.add(newLaboratory);
			i++;
		}
	}

	/**
	 * Set the vacancies of a particular laboratory in the lab list
	 * @param ID Identification number of the particular lab
	 */
	public void setLaboratoryVacancy(int ID) {

		laboratoryList.get(ID).setVacancies();

	}

	/**
	 * Set the vacancies of a particular tutorial in the tutorial list
	 * @param ID Identification number of the particular lab
	 */
	public void setTutorialVacancy(int ID) {

		tutorialList.get(ID).setVacancies();

	}

	/**
	 * Set the vacancies of a particular lecture in the lecture list
	 * @param ID Identification number of the particular lab
	 */
	public void setLectureVacancy(int ID) {
		lectureList.get(ID).setVacancies();
	}

	// CHECKING METHOD

	/**
	 * Check whether the course component values are valid
	 * @return true if the values are valid
	 */
	public boolean isCourseValid() {
		if (examWeight != 0 && courseworkWeight != null && numCoursework >= 0) {
			return true;
		} else {
			return false;
		}
	}
}