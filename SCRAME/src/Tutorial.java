/**
 * Tutorial class
 */
public class Tutorial extends Session {

    //CONSTRUCTOR
    public Tutorial(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Tutorial";
    }
}
