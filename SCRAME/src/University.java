import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;
import java.io.File;
import java.io.IOException;

/**
 * University application class
 */
public class University{
	
	private static Scanner sc = new Scanner(System.in);
	private static StudentManager studentManager = new StudentManager();
	private static ProfessorManager professorManager = new ProfessorManager();
	private static CourseManager courseManager = new CourseManager();
	private static RecordManager recordManager = new RecordManager();
	
	public static void main (String[] args) {
//		SerializeDB.writeSerializedObject("student.dat", studentManager.getStudentList());
//    	SerializeDB.writeSerializedObject("professor.dat", professorManager.getProfessorList());
//    	SerializeDB.writeSerializedObject("course.dat", courseManager.getCourseList());
//    	SerializeDB.writeSerializedObject("record.dat", recordManager.getRecordList());
		// Initialize a list of students from a file
		studentManager.readStudentList();
		// Initialize a list of professors from a file
		professorManager.readProfessorList();
		// Initialize a list of courses from a file
		courseManager.readCourseList();
		// Initialize a list of records from a file
		recordManager.readRecordList();
        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("     UNIVERSITY APPLICATION");
            System.out.println("     =========");

            System.out.println("  1. Add a student");
            System.out.println("  2. Add a professor");
            System.out.println("  3. Add a course ");
            System.out.println("  4. Print the student list");
            System.out.println("  5. Print the professor list");
            System.out.println("  6. Print the course list");
            System.out.println("  7. Check the vacancy of a class");
            System.out.println("  8. Register a student for a course ");
            System.out.println("  9. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println(" 10. Enter course assessment components weightage");
            System.out.println(" 11. Enter coursework mark");
            System.out.println(" 12. Enter exam mark");
            System.out.println(" 13. Print a student's transcript");
            
            System.out.println(" 14. Print a course statistics");
            System.out.println("  0. Exit");
            System.out.println();
            System.out.print("Option: ");

            boolean succeed = false;
            do {
                try {
                    choice = sc.nextInt();
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }
            } while(!succeed);

            switch(choice){
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    addStudent();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    studentManager.printStudentList();
                    break;
                case 5:
                    professorManager.printProfessorList();
                    break;
                case 6:
                    courseManager.printCourseList();
                    break;
                case 7:
                    checkSessionVacancies();
                    break;
                case 8:
                    registerStudentForCourse();
                    break;
                case 9:
                    printStudentNameListByCourseSession();
                    break;
                case 10:
                    setCourseComponentWeightage();
                    break;
                case 11:
                    setCourseworkMark();
                    break;
                case 12:
                    setExamMark();
                    break;
                case 13:
                    printStudentTranscript();
                    break;
                case 14:
                    printCourseStats();
                    break;
                default:
                    System.out.println("That is not a valid choice.");
                    break;
            }

        }
	}
	
    /**
     * Add a student
     */
    private static void addStudent(){
        sc.nextLine();
        boolean succeed = false;
        
        System.out.println("Enter the student's ID.");
        String inputID = null;
        int id = -1;

        do {
            try {
                // ID can only be an integer
            	inputID = sc.nextLine();
                id = Integer.parseInt(inputID);
                succeed = true;
            } catch (NumberFormatException e) {
            	System.out.println("Please enter an integer.");
            }
        }while(!succeed);
        succeed = false;
        
        System.out.println("Enter the student's name.");
        String name = null;
        do {
            try {
                // Name can only be alphabetical
                name = sc.nextLine();
                if (name.matches((".*\\d+.*"))) {
                    throw new ExceptionInvalidType("alphabets only");
                }
                succeed = true;
            } catch (ExceptionInvalidType e) {
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        // Check whether ID is duplicate
        if(studentManager.isExistingStudentID(id)){
            System.out.println("The name you have entered already exists. Are you sure you want to add this student?");
            System.out.println(" 1. Yes\t\t2. No");
            do {
                try {
                    int addStudent = sc.nextInt();
                    if(addStudent != 1 && addStudent != 2){
                        throw new ExceptionInvalidType("1 or 2");
                    } else if(addStudent == 1){
                        succeed = true;
                    } else {
                        return;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch (ExceptionInvalidType e){
                    System.out.println(e.getMessage());
                }
            }while(!succeed);
        }

        // Add the student into the database
        studentManager.addStudent(id, name);
        studentManager.writetStudentList();
        System.out.println("Student " + name + " has been registered with ID " + id + ".");
        studentManager.printStudentList();
    }
    
    /**
     * Add a professor
     */
    private static void addProfessor(){
        sc.nextLine();
        boolean succeed = false;
        
        System.out.println("Enter the professor's ID.");
        String inputID = null;
        int id = -1;

        do {
            try {
                // ID can only be an integer
            	inputID = sc.nextLine();
                id = Integer.parseInt(inputID);
                succeed = true;
            } catch (NumberFormatException e) {
            	System.out.println("Please enter an integer.");
            }
        }while(!succeed);
        succeed = false;
        
        System.out.println("Enter the professor's name. ");
        String name = null;

        do {
            try {
                // Name can only be alphabetical
                name = sc.nextLine();
                if (name.matches((".*\\d+.*"))) {
                    throw new ExceptionInvalidType("alphabets only");
                }
                succeed = true;
            } catch (ExceptionInvalidType e) {
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        // Check whether the ID is duplicate
        if(professorManager.isExistingProfessorID(id)){
            System.out.println("The name you have entered already exists. Are you sure you want to add this professor?");
            System.out.println(" 1. Yes\t\t2. No");
            do {
                try {
                    int addProf = sc.nextInt();
                    if(addProf != 1 && addProf != 2){
                        throw new ExceptionInvalidType("1 or 2");
                    } else if(addProf == 1){
                        succeed = true;
                    } else {
                        return;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch (ExceptionInvalidType e){
                    System.out.println(e.getMessage());
                }
            }while(!succeed);
        }
        
        // Add the professor into the database
        professorManager.addProfessor(id, name);
        professorManager.writeProfessorList();
        System.out.println("Professor " + name + " has been registered with ID " + id + ".");
        professorManager.printProfessorList();
    }
	
    
    /**
     * Add a course
     */
    private static void addCourse(){

        // Check whether there is at least one professor in the database
        try {
            if (professorManager.getProfessorList().size() == 0)
                throw new ExceptionNotSet("Professor");
        }catch(ExceptionNotSet e) {
            System.out.println("Please add a professor into the database before adding a course.");
            return;
        }
        boolean success = false;
        do{
            sc.nextLine();
            boolean succeed = false, succeed2 = false;
            int courseID = -1, professorID = -1, numLectures = -1, numTutorials = -1, numLabs = -1;
            int[] lectureCapacity = null;
            int[] tutorialCapacity = null;
            int[] labCapacity = null;

            System.out.println("Enter course name");
            String courseName = sc.nextLine().toUpperCase();
            // currently assume courseName has no space

            // Get the course ID
            do{
                try {
                    System.out.println("Enter ID of course below:");
                    courseManager.printCourseList();
                    courseID = sc.nextInt();
                    // Check whether course ID already exist in the database
                    if (courseManager.isExistingCourse(courseID)) {
                        throw new ExceptionDuplicate("Course");
                    }
                    succeed = true;
                }catch(InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch(ExceptionDuplicate eID){
                    System.out.println(eID.getMessage());
                }
            }while(!succeed);
            succeed = false;

            // Get the professor ID
            do{
                try{
                    System.out.println("Select ID from professors below:");
                    professorManager.printProfessorList();
                    professorID = sc.nextInt();
                    // Check whether professor ID already exist in the database
                    if (!professorManager.isExistingProfessorID(professorID)) {
                        throw new ExceptionInvalidID("Professor");
                    }
                    succeed = true;
                }catch(InputMismatchException e){
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                }catch(ExceptionInvalidID eID) {
                    System.out.println(eID.getMessage());
                }
            }while(!succeed);
            succeed = false;

            // Enter the number of lectures
            do {
                try {
                    System.out.println("Enter number of lectures (Minimum 1):");
                    numLectures = sc.nextInt();
                    // Minimum number of lectures is 1
                    if (numLectures < 1) {
                        throw new ExceptionInvalidNumber("lectures");
                    }
                    // Enter the capacity for each lecture session
                    lectureCapacity = new int[numLectures];
                    for (int i = 0; i < numLectures; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of lecture " + (i + 1) + " :");
                                lectureCapacity[i] = sc.nextInt();
                                //lecture capacity cannot be 0 or less
                                if (lectureCapacity[i] < 1) {
                                    throw new ExceptionInvalidNumber("lecture slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (ExceptionInvalidNumber eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (ExceptionInvalidNumber eID) {
                    System.out.println(eID.getMessage());
                }
            }while (!succeed) ;
            succeed = false;
            succeed2 = false;

            // Enter the number of tutorials
            do {
                try {
                    System.out.println("Enter number of tutorials:");
                    numTutorials = sc.nextInt();
                    if (numTutorials < 0)
                    	throw new ExceptionInvalidNumber("tutorials");
                    // Enter the capacity for each tutorial
                    tutorialCapacity = new int[numTutorials];
                    for (int i = 0; i < numTutorials; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of tutorial " + (i + 1) + " :");
                                tutorialCapacity[i] = sc.nextInt();
                                // Tutorial capacity cannot be less then 0
                                if (tutorialCapacity[i] < 0) {
                                    throw new ExceptionInvalidNumber("tutorial slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (ExceptionInvalidNumber eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    } 
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (ExceptionInvalidNumber eID) {
                    System.out.println(eID.getMessage());
                }
            } while (!succeed);
            succeed = false;
            succeed2 = false;

            // Enter the number of laboratories
            do {
                try {
                    System.out.println("Enter number of laboratories:");
                    numLabs = sc.nextInt();
                    if (numLabs < 0)
                    	throw new ExceptionInvalidNumber("laboratories");
                    // Enter the capacity for each laboratories
                    labCapacity = new int[numLabs];
                    for (int i = 0; i < numLabs; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of laboratory " + (i + 1) + " :");
                                labCapacity[i] = sc.nextInt();
                                // Laboratory capacity cannot be less than 0
                                if (labCapacity[i] < 0) {
                                    throw new ExceptionInvalidNumber("laboratory slots");
                                }
                                succeed2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter an integer.");
                                sc.next();
                            } catch (ExceptionInvalidNumber eID) {
                                System.out.println(eID.getMessage());
                            }
                        } while (!succeed2);
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (ExceptionInvalidNumber eID) {
                    System.out.println(eID.getMessage());
                }
            } while (!succeed);
            succeed = false;
            succeed2 = false;

            // Check all conditions of a course before adding it into the database
            success = professorManager.isExistingProfessorID(professorID) &&
                    numLectures >= 1 &&
                    numTutorials >= 0 &&
                    numLabs >= 0;

            if (!success) {
                System.out.println("Error: Please try again.");

            } else {
                courseManager.addCourse(courseID, courseName, professorID, lectureCapacity, tutorialCapacity, labCapacity);
                courseManager.writetCourseList();
                System.out.println("Course " + courseName + " with ID " + courseID + " has been created.");
            }

        }while(!success);
    }
    
    /**
     * Check for the vacancy in a particular session type
     */
    private static void checkSessionVacancies(){

        SessionOption sessionOption = SessionOption.LECTURE;
        int courseID = 0;
        boolean succeed = false;

        // Check whether there exist courses in the database
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new ExceptionNotSet("Course");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        // Enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                // Check whether the course exists
                if (!courseManager.isExistingCourse(courseID)) {
                    throw new ExceptionInvalidID("Course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidID e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        int sessionType = -1;

        // Choose the session option
        while(sessionType!=0) {
            System.out.println("Enter session option:");
            System.out.println("1. Lecture");
            System.out.println("2. Tutorial");
            System.out.println("3. Lab");
            System.out.println("0. Quit");

            do {
                try {
                	sessionType = sc.nextInt();
                    if (sessionType < 0 || sessionType > 3) {
                        throw new ExceptionInvalidNumber();
                    }
                    succeed = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer.");
                    sc.nextLine();
                } catch (ExceptionInvalidNumber e) {
                    System.out.println(e.getMessage());
                }
            } while (!succeed);


            // Session type conversion to enum
            if (sessionType == 1)
                sessionOption = SessionOption.LECTURE;
            else if (sessionType == 2)
            	sessionOption = SessionOption.TUTORIAL;
            else if (sessionType == 3)
            	sessionOption = SessionOption.LABORATORY;

            // Get the array of vacancies in the type of session selected in the particular course
            // An array is used because there can be more than one session of that type in the course
            int[] sessionVacancies = courseManager.getSessionVacancyByCourseID(courseID, sessionOption);
            int[] sessionCapacity =  courseManager.getSessionCapacityByCourseID(courseID, sessionOption);

            int numSessions = sessionVacancies.length;

            if(sessionType != 0) {
                // The session of that type is available in the particular course
                if (numSessions > 0) {
                    System.out.println("ID\tVacancies");
                    for (int i = 0; i < numSessions; i++) {
                        System.out.printf("%2d\t%-2d/%-5d\n", i, sessionVacancies[i], sessionCapacity[i]);
                    }
                }

                // There is no such type of session in the particular course if length equals 0
                else
                    System.out.println("There are no " + sessionOption.toString() + " available!");
            }
        }
    }
    
    /**
     * Register a student for a course
     */
    private static void registerStudentForCourse(){

        // Checks whether there exist courses in the database
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new ExceptionNotSet("Course");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add at least a course into the database before registration.");
            return;
        }

        // Check whether there exist students in the database
        try {
            if (studentManager.getStudentList().size() == 0)
                throw new ExceptionNotSet("Student");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add at least a student into the database before registration.");
            return;
        }

        boolean success = false;
        int studentID = -1, courseID = -1;

        // Enter the student's ID
        do {
            try {
                System.out.println("Enter ID of student:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                //checks if the student ID exists
                if (!studentManager.isExistingStudentID(studentID)) {
                    throw new ExceptionInvalidID("student");
                }
                success = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidID eID){
                System.out.println(eID.getMessage());
            }
        }while(!success);
        success = false;

        // Enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                // Check whether the course ID exists
                if(!courseManager.isExistingCourse(courseID)){
                    throw new ExceptionInvalidID("Course");
                }
                //checks if this course is ready for registration (which requires coursework weightage to be set)
                if (!courseManager.isCourseReadyForRegistrationByID(courseID)) {
                    throw new ExceptionNotSet("Course ID " + courseID);
                }
                success = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch (ExceptionInvalidID e){
                System.out.println(e.getMessage());
                return;
            }catch(ExceptionNotSet e){
                System.out.println(e.getMessage());
                return;
            }
        }while(!success);
        success = false;

        // Check whether student has already registered for the course
        try {
            if(recordManager.existingRecord(courseID, studentID)){
                throw new ExceptionDuplicate("Record");
            }
        } catch (ExceptionDuplicate e){
            System.out.println(e.getMessage());
            return;
        }

        // Select which lecture, tutorial and/or laboratory slot to register in
        SessionOption sessionOption = SessionOption.LECTURE;
        int numSessionTypes = SessionOption.getNumSessionType();
        int[] sessionChoice = new int[numSessionTypes];
        for (int i = 0; i < numSessionTypes; i++) {

            if (i == 0)
            	sessionOption = SessionOption.LECTURE;
            else if (i == 1)
            	sessionOption = SessionOption.TUTORIAL;
            else if (i == 2)
            	sessionOption = SessionOption.LABORATORY;

            int numSessions = courseManager.getSessionCapacityByCourseID(courseID, sessionOption).length;
            if (numSessions > 0) {
                int[] sessionVacancy = courseManager.getSessionVacancyByCourseID(courseID, sessionOption);
                do {
                    try {
                        int totalVacanciesLeft = 0;
                        for(int k = 0; k < sessionVacancy.length; k++){
                            totalVacanciesLeft += sessionVacancy[k];
                        }
                        if(totalVacanciesLeft <= 0){
                            throw new ExceptionFull();
                        }
                        System.out.println("Select a " + sessionOption.toString() + " ID");
                        System.out.println("ID\tVacancy");
                        for (int j = 0; j < sessionVacancy.length; j++) {
                            if (sessionVacancy[j] >= 0) {
                                System.out.printf("%2d\t%7d\n", j, sessionVacancy[j]);
                            }
                        }
                        sessionChoice[i] = sc.nextInt();
                        if (sessionChoice[i] < 0 || sessionChoice[i] >= sessionVacancy.length || sessionVacancy[sessionChoice[i]] == 0) {
                            throw new ExceptionInvalidID("session");
                        }
                        success = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter an integer.");
                        sc.nextLine();
                    } catch (ExceptionFull e){
                        System.out.println(e.getMessage());
                        return;
                    } catch (ExceptionInvalidID e) {
                        System.out.println(e.getMessage());
                    }
                } while (!success);
                success = false;
            } else {
            	sessionChoice[i] = -1;
            }
        }

        // Add the record into the database
        try {
            if (!recordManager.existingRecord(courseID, studentID)) {
                int numComponents = courseManager.getNumComponentsByCourseID(courseID);
                double examWeight = courseManager.getExamWeightByCourseID(courseID);
                double[] courseworkWeight = courseManager.getCourseworkWeightByCourseID(courseID);
                recordManager.addRecord(courseID, studentID, sessionChoice, numComponents, examWeight, courseworkWeight);
                recordManager.writetRecordList();
                System.out.println("Student ID " + studentID + " has successfully registered in Course ID " + courseID);
                for (int j = 0; j < numSessionTypes; j++) {
                    if (j == 0) {
                        sessionOption = SessionOption.LECTURE;
                    } else if (j == 1) {
                    	sessionOption = SessionOption.TUTORIAL;
                    } else if (j == 2) {
                    	sessionOption = SessionOption.LABORATORY;
                    }
                    if (sessionChoice[j] >= 0) {
                        courseManager.setVacancyByCourseSession(courseID, sessionOption, sessionChoice[j]);
                    }
                }
            } else {
                throw new ExceptionDuplicate("Record");
            }
        }catch(ExceptionDuplicate e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Print a list of all students in a particular course and session type
     */
    private static void printStudentNameListByCourseSession(){
        SessionOption sessionOption = SessionOption.LECTURE;
        int courseID = -1;
        boolean succeed = false;
        int sessionType = -1;
        int sessionID = -1;


        try {

            //enter the course ID
            do {
                try {
                    System.out.println("Select ID from courses below:");
                    courseManager.printCourseList();
                    courseID = sc.nextInt();
                    //check if course exists
                    if (!courseManager.isExistingCourse(courseID)) {
                        throw new ExceptionInvalidID("course");
                    }
                    succeed = true;
                }catch (InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }
            }while(!succeed);
            succeed = false;

            //enter lesson type
            do {
                try{
                    System.out.println("Enter session type:");
                    System.out.println("1. Lecture");
                    System.out.println("2. Tutorial");
                    System.out.println("3. Laboratory");
                    sessionType = sc.nextInt() - 1;
                    if (sessionType!=0 && sessionType!=1 && sessionType !=2 )
                        throw new ExceptionInvalidNumber();
                    if (sessionType == 0)
                    	sessionOption = SessionOption.LECTURE;
                    else if (sessionType == 1) {
                    	sessionOption = SessionOption.TUTORIAL;
                    }
                    else if (sessionType == 2) {
                    	sessionOption = SessionOption.LABORATORY;
                    }
                    if(courseManager.getSessionCapacityByCourseID(courseID, sessionOption).length == 0){
                        throw new ExceptionNotSet("Session");
                    }
                    succeed = true;
                }catch (InputMismatchException e) {
                    System.out.println("Wrong input type.");
                    sc.nextLine();
                }catch(ExceptionInvalidNumber e){
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }catch (ExceptionNotSet e){
                    System.out.println("There is no session of this session type.");
                }
            }while(!succeed);
            succeed = false;

            //enter the lesson ID after selecting the lesson type
            int[] studentIDList = null;
            int numLessons = courseManager.getSessionCapacityByCourseID(courseID, sessionOption).length;
            if (numLessons > 0) {
                do{
                    try {
                        System.out.println("Select a " + sessionOption.toString() + " ID");
                        for (int i = 0; i < numLessons; i++) {
                            System.out.printf("%d\n", i);
                        }
                        sessionID = sc.nextInt();
                        succeed = true;
                    }catch(InputMismatchException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                }while(!succeed);
                succeed = false;

            }
            //get student ID list and print it out
            studentIDList = recordManager.getStudentIDListByCourseSession(courseID, sessionOption, sessionID);
            if(studentIDList.length>0) {
                System.out.printf(" ID\tStudent Name\n");
                for (int i = 0; i < studentIDList.length; i++) {
                    System.out.printf("%3d\t%-30s\n", studentIDList[i], studentManager.getStudent(studentIDList[i]).getStudentName());
                }
            } else {
                System.out.println("There are no students in this lesson");
            }
        }catch (ExceptionInvalidID e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Set the weightage of all course components
     */
    private static void setCourseComponentWeightage(){

        boolean succeed = false, succeed2 = false;
        int numberOfCoursework = -1, courseID = -1;
        double examWeight = -1, counter = 0;
        double[] courseworkWeight = null;

        // Check whether there are courses existing in the database
        try{
            if(courseManager.getCourseIDList().length == 0){
                throw new ExceptionNotSet("Course");
            }
        }catch(ExceptionNotSet e){
            System.out.println(e.getMessage());
            return;
        }

        // Enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                // Check whether the course ID exists in the database
                if (!courseManager.isExistingCourse(courseID)) {
                    throw new ExceptionInvalidID("course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidID e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        // If the course has been register by at least one student, the course component weightage cannot be changed
        try {
            if (recordManager.getNumStudentsByCourseID(courseID) > 0) {
                throw new ExceptionCourseWeightage();
            }
        }catch(ExceptionCourseWeightage e){
            System.out.println(e.getMessage());
            return;
        }

        // Get the weightage of the exam component
        do {
            try {
                System.out.println("Enter weightage of Exam component (out of 100%):");
                examWeight = sc.nextDouble() / 100;
                // Check whether the weightage is out of bounds
                if (examWeight < 0 || examWeight > 1) {
                    throw new ExceptionInvalidNumber();
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter a number from 0 to 100.");
                sc.nextLine();
            }catch(ExceptionInvalidNumber e){
                System.out.println("Please enter a number from 0 to 100.");
            }
        }while(!succeed);
        succeed = false;

        // Get number of coursework components
        do {
            try {
                System.out.println("Enter number of coursework components:");
                numberOfCoursework = sc.nextInt();
                // If examWeight is not 100%, number of courseworks cannot be less than one
                if (numberOfCoursework < 1 && examWeight != 1) {
                    throw new ExceptionInvalidNumber();
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidNumber e){
                System.out.println("Please enter a number larger than 0.");
            }
        }while(!succeed);
        succeed = false;

        // Get weightage of each course component
        do {
            counter = 0;
            courseworkWeight = new double[numberOfCoursework];
            for (int i = 0; i < numberOfCoursework; i++) {
                do {
                    try {
                        // Set the last coursework component weightage immediately
                        if(i==numberOfCoursework-1){
                            System.out.println("Weightage of component[" + (i+1) + "] is " + (100-counter) + ".");
                            courseworkWeight[i] = (100-counter)/100;
                        }else {
                            // Allow the user to set the coursework component for all except the last component, which will be set automatically
                            System.out.println("Enter weightage of component[" + (i + 1) + "] out of " + numberOfCoursework + ": (from 0% to " + (100 - counter) + "%)");
                            courseworkWeight[i] = sc.nextDouble()/100;
                            if (courseworkWeight[i] < 0.0 || courseworkWeight[i] > (100 - counter)) {
                                throw new ExceptionInvalidNumber();
                            }
                        }
                        counter += courseworkWeight[i]*100;
                        succeed = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number.");
                        sc.nextLine();
                    } catch (ExceptionInvalidNumber e) {
                        System.out.println(e.getMessage());
                    }
                } while (!succeed);
                succeed = false;
                succeed2 = true;
            }
        }while(!succeed2);

        courseManager.setComponentWeightByCourseID(courseID, examWeight, courseworkWeight);
    }
    
    /**
     * Set coursework mark
     */
    private static void setCourseworkMark(){

        // Check whether there are courses in the database
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new ExceptionNotSet("Course");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        // Check whether there are students in the database
        try {
            if (studentManager.getStudentList().size() == 0)
                throw new ExceptionNotSet("Student");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add a student into the database before registration.");
            return;
        }

        // Check whether there are records in the database
        try {
            if (recordManager.getNumRecords() == 0)
                throw new ExceptionNotSet("Record");
        } catch(ExceptionNotSet e) {
            System.out.println("There are no records in the database.");
            return;
        }

        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double[] courseworkMark;

        // Enter the student ID
        do {
            try {
                System.out.println("Enter ID of student below:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                // Check whether the student ID exists
                if (!studentManager.isExistingStudentID(studentID))
                    throw new ExceptionInvalidID("student");
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch(ExceptionInvalidID e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while (!succeed);
        succeed = false;

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //check that the course ID exists
                if (!courseManager.isExistingCourse(courseID))
                    throw new ExceptionInvalidID("course");
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch (ExceptionInvalidID e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while (!succeed);
        succeed = false;

        // Set the mark for each component in the course for the student
        do {
            try {
                int numberOfComponents = courseManager.getNumComponentsByCourseID(courseID);
                if (numberOfComponents == -1)
                    throw new ExceptionInvalidID("Course");
                courseworkMark = new double[numberOfComponents];
                //loop through the components and enter the marks
                for (int i = 0; i < numberOfComponents; i++) {
                    System.out.println("Enter marks for component[" + i + "]:");
                    courseworkMark[i] = sc.nextDouble();
                    if (courseworkMark[i] < 0 || courseworkMark[i] > 100) {
                        throw new ExceptionInvalidNumber();
                    }
                }
                succeed = true;
                recordManager.setCourseworkMark(courseID, studentID, courseworkMark);
                recordManager.writetRecordList();
            } catch (ExceptionInvalidID e) {
                System.out.println(e.getMessage());
            } catch (ExceptionInvalidNumber e) {
                System.out.println("Please enter a number from 0 to 100.");
            }
        } while (!succeed);
    }
    
    /**
     * Set exam mark
     */
    private static void setExamMark() {
        int studentID = -1;
        int courseID = -1;
        boolean succeed = false;
        double examMark = -1;

        // Check whether there exists records in the database 
        try{
            if(recordManager.getNumRecords() == 0){
                throw new ExceptionNotSet("Record");
            }
        }catch(ExceptionNotSet e){
            System.out.println(e.getMessage());
            return;
        }

        // Enter the student ID
        do {
            try {
                System.out.println("Enter ID of student below:");
                studentManager.printStudentList();
                studentID = sc.nextInt();
                // Check whether the student ID exists
                if (!studentManager.isExistingStudentID(studentID))
                    throw new ExceptionInvalidID("student");
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch (ExceptionInvalidID e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while(!succeed);
        succeed = false;

        // Enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                // Check whether the course ID exists
                if(!courseManager.isExistingCourse(courseID)){
                    throw new ExceptionInvalidID("course");
                }
                succeed = true;
            }catch(InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            }catch(ExceptionInvalidID e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while(!succeed);
        succeed = false;

        // Set the mark for exam
        do {
            try {
                System.out.println("Enter marks for exam:");
                examMark = sc.nextDouble();
                //check that the exam marks is valid
                if (examMark < 0 || examMark > 100)
                    throw new ExceptionInvalidNumber();
                recordManager.setExamMark(courseID, studentID, examMark);
                recordManager.writetRecordList();
                succeed = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input type.");
                sc.nextLine();
            } catch (ExceptionInvalidNumber e) {
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;
    }
    
    /**
     * Print the record of a particular student
     */
    private static void printStudentTranscript(){

        boolean succeed = false;
        int studentID = -1;
        int[] courseIDList = null;

        // Check whether there exist records in the database before proceeding
        try{
            if(recordManager.getNumRecords() == 0){
                throw new ExceptionNotSet("Record");
            }
        }catch(ExceptionNotSet e){
            System.out.println(e.getMessage());
            return;
        }

        // Enter the student ID
        do {
            try {
                System.out.println("Enter ID of student");
                studentID = sc.nextInt();
                // Check whether the student ID exists
                if (!studentManager.isExistingStudentID(studentID)) {
                    throw new ExceptionInvalidID("student");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidID e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);
        succeed = false;

        // Check whether the student has at least one record in the database
        try {
            courseIDList = recordManager.getCourseIDByStudentID(studentID);
            if (courseIDList == null) {
                throw new ExceptionNotSet("Record of student ID " + studentID);
            }
        }catch(ExceptionNotSet e){
            System.out.println("There is no record for this student.");
            return;
        }

        // Loop through the record and print out the statistics
        for (int i = 0; i < recordManager.getNumCourseByStudentID(studentID); i++) {
            System.out.printf("Course Name  : %s\n", courseIDList[i]);
            if (recordManager.isMarked(courseIDList[i], studentID)) {
                System.out.printf("Grade        : %s\n", recordManager.getGradeByCourseStudentID(courseIDList[i], studentID));
                System.out.printf("Overall Mark: %.1f\n", recordManager.getOverallMarkByCourseStudentID(courseIDList[i], studentID));
                System.out.printf("Exam Mark   : %.1f\n", recordManager.getExamMarkByCourseStudentID(courseIDList[i], studentID));
                System.out.printf("Exam Weight  : %.1f percent\n", courseManager.getExamWeightByCourseID(courseIDList[i])*100);
                double[] courseworkMarks = recordManager.getCourseworkMarkByCourseStudentID(courseIDList[i], studentID);
                double[] courseworkWeight = courseManager.getCourseworkWeightByCourseID(courseIDList[i]);
                for (int j = 0; j < courseworkMarks.length; j++) {
                    System.out.printf("Coursework[%d] Mark : %.1f\n", j+1, courseworkMarks[j]);
                    System.out.printf("Coursework[%d] Weight: %.1f percent\n", j+1, courseworkWeight[j] * (1 - courseManager.getExamWeightByCourseID(courseIDList[i])) * 100);
                }
                System.out.println();

            } else {
                System.out.println("Not marked");
            }
        }
        System.out.println("End of Transcript");
    }
    
    /**
     * Print the statistics of a particular course
     */
    private static void printCourseStats(){

        // Check whether there exist a course in the database
        try {
            if (courseManager.getCourseIDList().length == 0)
                throw new ExceptionNotSet("Course");
        } catch(ExceptionNotSet e) {
            System.out.println("Please add a course into the database before registration.");
            return;
        }

        int courseID = 0;
        boolean succeed = false;

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //if the courseID is not valid the function will throw the invalid ID exception
                if (!courseManager.isExistingCourse(courseID)) {
                    throw new ExceptionInvalidID("course");
                }
                succeed = true;
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer.");
                sc.nextLine();
            }catch(ExceptionInvalidID e){
                System.out.println(e.getMessage());
            }
        }while(!succeed);

        //getting the number of students in the course selected by the user
        int numStudents = recordManager.getNumStudentsByCourseID(courseID);
        System.out.printf("Number of students: %d\n", numStudents);

        //getting the average overall marks, of the course selected by the user
        double averageOverallMarks = recordManager.getAverageOverallMarkByCourseID(courseID);
        System.out.printf("Average Overall Marks: %.1f\n", averageOverallMarks);

        //getting the average exam marks of the course selected by the user
        double averageExamMarks = recordManager.getAverageExamMarkByCourseID(courseID);
        System.out.printf("Average Exam Marks: %.1f\n", averageExamMarks);

        //getting the average marks of all the course works of the course selected by the user
        double averageTotalCourseworkMarks = recordManager.getAverageTotalCourseworkMarkByCourseID(courseID);
        System.out.printf("Average Total Coursework Marks: %.1f\n", averageTotalCourseworkMarks);
        
        System.out.println("End of Course Statistics");
    }
}