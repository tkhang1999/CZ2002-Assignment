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
            System.out.println("     MAIN MENU");
            System.out.println("     =========");

            System.out.println("  1. Add a student");
            System.out.println("  2. Add a professor");
            System.out.println("  3. Add a course ");
            System.out.println("  4. Enter course assessment components weightage");
            System.out.println("  5. Register Student for a course ");
            System.out.println("  6. Print student list by lecture, tutorial or laboratory session for a course");
            System.out.println("  7. Check available slot in a class (vacancy in a class)");
            System.out.println("  8. Enter coursework mark - inclusive of its components");
            System.out.println("  9. Enter exam mark");
            System.out.println(" 10. Print student transcript");
            System.out.println(" 11. Print course statistics");

            System.out.println(" 12. Print student list");
            System.out.println(" 13. Print professor list");
            System.out.println(" 14. Print course list");

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
                    setCourseComponentWeightage();
                    break;
                case 5:
                    registerStudentForCourse();
                    break;
                case 6:
                    printStudentNameListByCourseSession();
                    break;
                case 7:
                    checkSessionVacancies();
                    break;
                case 8:
                    setCourseworkMark();
                    break;
                case 9:
                    setExamMark();
                    break;
                case 10:
                    printStudentTranscript();
                    break;
                case 11:
                    printCourseStats();
                    break;
                case 12:
                    studentManager.printStudentList();
                    break;
                case 13:
                    professorManager.printProfessorList();
                    break;
                case 14:
                    courseManager.printCourseList();
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
                //check that ID can only be an integer
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
                //check that names can only be alphabetical
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

        //handles duplicate names, allow user to confirm adding in
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
                //check that ID can only be an integer
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
                //check that name can only be alphabetical
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

        //handle duplicate names, allows user to confirm adding in
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

            //get the professor's ID
            do{
                try{
                    System.out.println("Select ID from professors below:");
                    professorManager.printProfessorList();
                    professorID = sc.nextInt();
                    //check that the professor ID does not already exist in the database
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

            //enter the number of lectures
            do {
                try {
                    System.out.println("Enter number of lectures (Minimum 1):");
                    numLectures = sc.nextInt();
                    //minimum number of lectures is 1
                    if (numLectures < 1) {
                        throw new ExceptionInvalidNumber("lectures");
                    }
                    //enter the lecture capacity for each lecture
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
                    //enter the tutorial capacity for each tutorial
                    tutorialCapacity = new int[numTutorials];
                    for (int i = 0; i < numTutorials; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of tutorial " + (i + 1) + " :");
                                tutorialCapacity[i] = sc.nextInt();
                                //tutorial capacity cannot be 0 or less
                                if (tutorialCapacity[i] < 1) {
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

            //enter the number of labs
            do {
                try {
                    System.out.println("Enter number of laboratories:");
                    numLabs = sc.nextInt();
                    if (numLabs < 0)
                    	throw new ExceptionInvalidNumber("laboratories");
                    //enter the lab capacity for each lab
                    labCapacity = new int[numLabs];
                    for (int i = 0; i < numLabs; i++) {
                        succeed2 = false;
                        do {
                            try {
                                System.out.println("Enter capacity of laboratory " + (i + 1) + " :");
                                labCapacity[i] = sc.nextInt();
                                //lab capacity cannot be 0 or less
                                if (labCapacity[i] < 1) {
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
                System.out.println("Course " + courseName + " with ID " + courseID + " has been created.");
            }

        }while(!success);
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

        //enter the course ID
        do {
            try {
                System.out.println("Select ID from courses below:");
                courseManager.printCourseList();
                courseID = sc.nextInt();
                //checks if the course ID exists in the database
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

        //if the course has been register by at least one student, the course component weightage cannot be changed
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
}
    
    
    