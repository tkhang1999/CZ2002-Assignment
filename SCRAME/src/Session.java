import java.io.Serializable;
/**
 * Store and get Session information
 */
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3601685529344494294L;
	// protected variables for subclass access
    protected int ID;
    protected int capacity;
    protected int vacancies;
    protected String name;

    //CONSTRUCTOR

    /**
     * initialize vacancy = capacity. No registration yet
     * @param ID identification number of particular session
     * @param capacity number of students allowed to take this particular session
     */
    public Session(int ID, int capacity){
        this.ID = ID;
        this.capacity = capacity;
        this.vacancies = capacity;
    }

    //GET METHODS
    /**
     * Retrieves the number of vacancies for particular session
     * @return number of vacancies for particular session
     */
    public int getVacancies(){
        return this.vacancies;
    }

    /**
     * Retrieves the identification number of particular session
     * @return session ID
     */
    public int getID(){
        return this.ID;
    }

    /**
     * Retrieves capacity of particular session
     * @return session capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * @return student name
     */
    public String getName(){
        return this.name;
    }

    //SET METHODS
    /**
     * minus vacancy by 1
     */
    public void setVacancies(){
        vacancies--;
    }
}
