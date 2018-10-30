import java.io.Serializable;
/**
 * Tutorial class
 */
public class Tutorial extends Session implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4044634689841303660L;

	//CONSTRUCTOR
    public Tutorial(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Tutorial";
    }
}
