import java.util.List;
import java.util.ArrayList;

public class ProfessorManager {

	private List<Professor> professorList = new ArrayList<Professor>();
	
	// GET METHODS
	
    /**
     * Retrieves list of professors
     * @return list of professors
     */
    public List<Professor> getProfessorList(){
        return professorList;
    }
	
    /**
     * print out the list of professor IDs and professor names
     */
    public void printProfessorList(){
        System.out.printf(" ID\tProfessor Name\n");
        for(int i = 0; i<getProfessorList().size(); i++){
            System.out.printf("%3d\t%-30s\n", getProfessorList().get(i).getProfessorID(), getProfessorList().get(i).getProfessorName().toUpperCase());
        }
    }
    
    /**
     * Retrieve professor information by professorID
     * @param professorID identification number of professor
     * @return professor object or null
     */
    public Professor getProfessor(int professorID) {
    	for (int i=0; i<professorList.size(); i++) {
    		Professor professor = professorList.get(i);
    		if (professor.getProfessorID() == professorID)
    			return professor;
    	}
    	return null;
    }
    
    // SET METHOD
    
    /**
     * add professor to professorList
     * @param professorName name of the professor
     * @param professorID identification number of the professor
     */
    public void addProfessor (int professorID, String professorName) {
        professorList.add(new Professor(professorName, professorID));
    }
    
    // CHECKING METHOD
    
    /**
     * checks if the given professorID already exists in the database
     * @param professorID identification number of the professor
     * @return true if professorID already exists in database
     */
    public boolean isExistingProfessorID (int professorID){
        if (getProfessor(professorID) != null)
        	return true;
        else {
        	return false;
        }
    }
}
