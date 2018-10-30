import java.io.Serializable;
/**
 * Lecture class
 */
public class Lecture extends Session implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3411518887498870802L;

	//CONSTRUCTOR
    public Lecture(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Lecture";
    }
}