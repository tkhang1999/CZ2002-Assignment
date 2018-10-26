/*
 * Stores and retrieves record data
 */
public class Record {
	
	private int courseID;
	private int studentID;
	
	private int lectureChoice;
	private int tutorialChoice;
	private int laboratoryChoice;
	
	private double examWeight;
	private double[] courseworkWeight;
	
	private double examMark;
	private double[] courseworkMark;
	private double totalCourseworkMark;
	private double overallMark;
	private String grade;
	
    private boolean examMarked = false;
    private boolean courseworkMarked = false;
    private boolean overallMarked = false;
    
    // CONSTRUCTOR

    /**
     * Constructor for record
     * @param courseID identification number of the courses the student has registered
     * @param studentID identification number of the student
     * @param SessionChoice identification number of the lab,tutorial or laboratory the student is registered to
     * @param numComponents number of coursework components 
     * @param examWeight weightage of the exam mark
     * @param courseworkWeight weightage of coursework mark
     */
    public Record (int courseID,int studentID, int[] SessionChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.lectureChoice = SessionChoice[0];
        this.tutorialChoice = SessionChoice[1];
        this.laboratoryChoice = SessionChoice[2];
        this.courseworkMark = new double[numComponents];
        this.examWeight = examWeight;
        this.courseworkWeight = courseworkWeight;
    }
        
    // GET METHODS
    
    /**
     * Get course ID
     * @return courseID
     */
    public int getCourseID() {
    	return courseID;
    }
    
    /**
     * Get student ID
     * @return studentID
     */
    public int getStudentID() {
    	return studentID;
    }
    
    /**
     * Get a list of all coursework components
     * @return courseworkMark 
     */
    public double[] getCourseworkMark() {
    	return courseworkMark;
    }
    
    /**
     * Get total coursework mark
     * @return totalCourseworkMark
     */
    public double getTotalCourseworkMark() {
    	return totalCourseworkMark;
    }
    
    /**
     * Get exam mark
     * @return examMark
     */
    public double getExamMark() {
    	return examMark;
    }
    
    /**
     * Get overall mark
     * @return overallMark
     */
    public double getOverallMark() {
    	return overallMark;
    }
    
    /**
     * Get grade
     * @return grade
     */
    public String getGrade() {
    	return grade;
    }
    
    /**
     * Get lecture choice
     * @return lectureChoice
     */
    public int getLectureChoice() {
    	return lectureChoice;
    }
    
    /**
     * Get tutorial choice
     * @return tutorialChoice
     */
    public int getTutorialChoice() {
    	return tutorialChoice;
    }

    /**
     * Get laboratory choice
     * @return laboratoryChoice
     */
    public int getLaboratoryChoice() {
    	return laboratoryChoice;
    }
    
    // SET METHODS
    
    /**
     * Set the mark of all coursework components
     * @param courseworktMark mark of all coursework components
     */
    public void setCourseworkMark(double[] courseworkMark) {
    	if (this.courseworkMark.length == courseworkMark.length) {
        	for (int i=0; i<courseworkMark.length; i++) {
        		this.courseworkMark[i] = courseworkMark[i];
        	}
        	this.courseworkMarked = true;
        	setTotalCourseworkMark();
        	setOverallMarked();
    	}
    }
    
    /**
     * Set the total mark of all coursework components
     */
    public void setTotalCourseworkMark() {
    	double total = 0;
    	for (int i=0; i<courseworkMark.length; i++) {
    		total += courseworkMark[i]*courseworkWeight[i];
    	}
    	totalCourseworkMark = total;
    }
    
    /**
     * Set the exam mark
     * @param examMark mark of the exam
     */
    public void setExamMark(double examMark) {
    	this.examMark = examMark;
    	this.examMarked = true;
    	setOverallMarked();
    }
    
    /**
     * Set the overall mark to be marked if both exam and coursework marks are marked
     */
    private void setOverallMarked() {
    	overallMarked = courseworkMarked && examMarked;
    	if (overallMarked) {
    		setOverallMark();
    		setGrade();
    	}
    }
    
    /**
     * Set the overall mark using exam mark and coursework mark
     */
    private void setOverallMark() {
    	overallMark = examMark*examWeight + totalCourseworkMark*(1-examWeight);
    }
    
    /**
     * Set the grade using overall mark
     */
    private void setGrade() {
    	if (overallMark >= 80)
    		grade = "A";
    	else if (overallMark >= 70)
    		grade = "B";
    	else if (overallMark >= 60)
    		grade = "C";
    	else if (overallMark >= 50)
    		grade = "D";
    	else
    		grade = "F";
    }
    
    // CHECKING METHOD
    
    public boolean isMarked() {
    	return overallMarked;
    }
}
