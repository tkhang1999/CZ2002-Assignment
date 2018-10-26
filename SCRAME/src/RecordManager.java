import java.util.List;
import java.util.ArrayList;
/**
 * Logic control of record information
 */
public class RecordManager {
	
	private List<Record> recordList = new ArrayList<Record>();
	
	// GET METHODS
	
	/**
	 * Get the list of all records
	 * @return list of record objects
	 */
	public List<Record> getRecordList(){
		return recordList;
	}
	
    /**
     * Get the number of records
     * @return number of records
     */
    public int getNumRecords(){
        return getRecordList().size();
    }
	
    /**
     * Get the record using courseID and studentID
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return record of a particular student taking a particular course
     */
    public Record getRecord (int courseID, int studentID){
        for(int i = 0; i < recordList.size(); i++){
            Record currentRecord = recordList.get(i);
            if(currentRecord.getStudentID()==(studentID) && currentRecord.getCourseID()==(courseID)){
                return currentRecord;
            }
        }
        return null;
    }

	/**
	 * Get the list of records using courseID
	 * @param courseID course identification number
	 * @return the list the corresponding record objects
	 */
	public Record[] getRecordsByCourseID(int courseID) {
        List<Record> courseRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){

            // If record is of the selected course ID, add record to list of selected records
            if(recordList.get(i).getCourseID()==(courseID)){
                courseRecords.add(recordList.get(i));
            }
        }

        // Return null if there are no students registered in this course
        if(courseRecords.size() == 0){
            return null;
        }

        // Convert the list of selected records into an array of records
        Record[] records= new Record[courseRecords.size()];
        for(int j = 0; j < records.length; j++){
            records[j] = courseRecords.get(j);
        }
        return records;
	}
	
    /**
     * Get the number of students using courseID
     * @param courseID identification number of course
     * @return the number of students registered in the course
     */
    public int getNumStudentsByCourseID (int courseID){
        if(getRecordsByCourseID(courseID) == null){
            return 0;
        }
        return getRecordsByCourseID(courseID).length;
    }
	
	/**
	 * Get the list of records using student ID
	 * @param studentID student identification number
	 * @return the list of the corresponding objects
	 */
    public Record[] getRecordsByStudentID(int studentID){
        List<Record> studentRecords = new ArrayList<Record>();
        for(int i = 0; i < recordList.size(); i++){

            // If record is of the selected student, add record to list of selected records
            if(recordList.get(i).getStudentID()==(studentID)){
            	studentRecords.add(recordList.get(i));
            }
        }

        // Return null if the selected student does not have any registered courses
        if(studentRecords.size() == 0){
            return null;
        }

        // Convert the list of selected records into an array of records
        Record[] records = new Record[studentRecords.size()];
        for(int j = 0; j < records.length; j++){
            records[j] = studentRecords.get(j);
        }
        return records;
    }
    
    /**
     * Get the list of course IDs using student ID
     * @param studentID identification number of student
     * @return the list of course IDs registered by the student
     */
    public int[] getCourseIDByStudentID(int studentID){

        // Retrieve records for selected student
        Record[] records = getRecordsByStudentID(studentID);
        if(records == null){
            return null;
        }

        // Create an integer array to store course IDs from the selected student
        int[] courseIDList = new int[records.length];
        for(int i = 0; i < records.length; i++){
            courseIDList[i] = records[i].getCourseID();
        }
        return courseIDList;
    }
    
    /**
     * Get the number of courses using studentID
     * @param studentID identification number of student
     * @return the number of courses taken by particular student
     */
    public int getNumCourseByStudentID(int studentID){
        return getRecordsByStudentID(studentID).length;
    }
    
    /**
     * Get the list of student IDs for selected session in a course
     * @param courseID identification number of the course
     * @param SessionOption ENUM value of LECTURE, LAB, TUTORIAL
     * @param SessionID identification number of the Session type chosen
     * @return the list of student IDs for selected session in a course
     */
    public int[] getStudentIDListByCourseSession (int courseID, SessionOption SessionOption, int SessionID){
        List<Record> selectedRecords = new ArrayList<>();

        // Traverse through all the records to search for records relating to the course, session type and session ID
        for (Record currentRecord : recordList) {
            if (currentRecord.getCourseID() == courseID) {
                switch (SessionOption) {
                    case LECTURE:
                        if (currentRecord.getLectureChoice() == SessionID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case TUTORIAL:
                        if (currentRecord.getTutorialChoice() == SessionID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    case LABORATORY:
                        if (currentRecord.getLaboratoryChoice() == SessionID) {

                            //Add to list of selected records
                            selectedRecords.add(currentRecord);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        
        // Create an integer array to store student IDs from the list of selected records

        int[] studentNameList = new int[selectedRecords.size()];
        for(int j = 0; j < selectedRecords.size(); j++){
            studentNameList[j] = selectedRecords.get(j).getStudentID();
        }
        return studentNameList;
    }
    
    /**
     * Get all coursework marks using courseID and studentID
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return the list of coursework marks of a particular student for a particular course
     */
    public double[] getCourseworkMarksByCourseStudent(int courseID, int studentID){
        return getRecord(courseID, studentID).getCourseworkMark();
    }
    
    /**
     * Get the exam mark using courseID and studentID
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return the exam mark of a particular student of a particular course
     */
    public double getExamMarksByCourseStudent(int courseID, int studentID){
        return getRecord(courseID, studentID).getExamMark();
    }
    
    /**
     * Get the overall mark using courseID and studentID
     * @param courseID identification number of course
     * @param studentID identification number of the student
     * @return the overall mark of a particular student for a particular course
     */
    public double getOverallMarksByCourseStudent(int courseID, int studentID){
        return getRecord(courseID, studentID).getOverallMark();
    }
    
    /**
     * Get the grade using courseID and studentID
     * @param courseID identification number of course
     * @param studentID identification number of student
     * @return the grade of particular student in a particular course
     */
    public String getGradeByCourseStudent(int courseID, int studentID){
        return getRecord(courseID, studentID).getGrade();
    }
    
    /**
     * Get the average mark for coursework components using courseID
     * @param courseID identification number of course
     * @return the average coursework mark for the course
     */
    public double getAverageTotalCourseworkMarksByCourseID(int courseID){
        Record[] courseRecords = getRecordsByCourseID(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getTotalCourseworkMark();
        }
        return sum/courseRecords.length;
    }
    
    /**
     * Get the average exam mark using courseID
     * @param courseID identification number of course
     * @return the average exam mark for the course
     */
    public double getAverageExamMarksByCourseID(int courseID){
        Record[] courseRecords = getRecordsByCourseID(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getExamMark();
        }
        return sum/courseRecords.length;
    }

    /**
     * Get the average overall mark using courseID
     * @param courseID identification number of course
     * @return the average overall mark of the course
     */
    public double getAverageOverallMarksByCourseID(int courseID){
        Record[] courseRecords = getRecordsByCourseID(courseID);
        double sum = 0;
        if(courseRecords == null){
            return 0;
        }
        for (Record courseRecord : courseRecords) {
            sum += courseRecord.getOverallMark();
        }
        return sum/courseRecords.length;
    }

    // SET METHODS

    /**
     * Add a new record to record database
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @param SessionChoice identification number of the lecture,tutorial or laboratory the student is registered to
     * @param numComponents number of components of coursework
     * @param examWeight weightage of the exam marks
     * @param courseworkWeight weightage of coursework marks
     */
    public void addRecord (int courseID, int studentID, int[] SessionChoice, int numComponents, double examWeight, double[] courseworkWeight) {
        recordList.add(new Record(courseID, studentID, SessionChoice, numComponents, examWeight, courseworkWeight));
    }
    
    /**
     * Set the mark of all coursework components using studentID and courseID
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @param courseworkMark marks of the coursework components
     */
    public void setCourseworkMark(int courseID, int studentID, double[] courseworkMark){
        getRecord(courseID, studentID).setCourseworkMark(courseworkMark);
    }
    
    /**
     * Set the exam mark using courseID and student ID
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @param examMark mark of the exam
     */
    public void setExamMark(int courseID, int studentID, double examMark){
        getRecord(courseID, studentID).setExamMark(examMark);
    }
    
    // CHECKING METHODS
    
    /**
     * Check whether a record already exists using course ID and studentID
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @return true if a record already exists
     */
    public boolean existingRecord (int courseID, int studentID) {
        boolean result = false;
        for (Record record : recordList) {
            if (record.getCourseID() == courseID && (record.getStudentID() == studentID)) {

                //Returns true if a record with the same course ID and student ID already exists
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Check whether a record has been marked and ready to be printed in transcript
     * @param courseID identification number of the course
     * @param studentID identification number of the student
     * @return true if marked
     */
    public boolean isMarked(int courseID, int studentID){
        return getRecord(courseID, studentID).isMarked();
    }
}
