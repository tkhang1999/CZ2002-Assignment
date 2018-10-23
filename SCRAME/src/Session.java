/**
 * protected variables for subclass access
 */
public class Session {

    protected int ID;
    protected int capacity;
    protected int vacancies;
    protected String name;

    //CONSTRUCTOR

    /**
     * initialize vacancy = capacity. No registration yet
     * @param ID identification number of particular lesson
     * @param capacity number of students allowed to take this particular lesson
     */
    public Session(int ID, int capacity){
        this.ID = ID;
        this.capacity = capacity;
        this.vacancies = capacity;
    }

    //GET METHODS
    /**
     * Retrieves the number of vacancies for particular lesson
     * @return number of vacancies for particular lesson
     */
    public int getVacancies(){
        return this.vacancies;
    }

    /**
     * Retrieves the identification number of particular lesson
     * @return lesson ID
     */
    public int getID(){
        return this.ID;
    }

    /**
     * Retrieves capacity of particular lesson
     * @return lesson capacity
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
