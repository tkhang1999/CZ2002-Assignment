import java.io.Serializable;
/**
 * Store and get professor information
 */
public class Professor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5064730103652530779L;
	private int professorID;
    private String professorName;

    //CONSTRUCTOR

    /**
     * Constructor for Professor
     * @param professorName name of professor
     * @param professorID identification number of professor
     */
    public Professor (String professorName, int professorID) {
        this.professorName = professorName;
        this.professorID = professorID;
    }

    //GET METHODS

    /**
     * Get identification number of professor
     * @return professor identification number
     */
    public int getProfessorID() {
        return professorID;
    }

    /**
     * Get professor name
     * @return professor name
     */
    public String getProfessorName() {
        return professorName;
    }
}