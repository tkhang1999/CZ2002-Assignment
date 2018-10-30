import java.io.Serializable;
/**
 * Laboratory class
 */
public class Laboratory extends Session implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8135117839882216259L;

	//CONSTRUCTOR
    public Laboratory(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Laboratory";
    }
}