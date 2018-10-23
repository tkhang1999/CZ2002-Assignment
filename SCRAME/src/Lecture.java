
public class Lecture extends Session {

    //CONSTRUCTOR
    public Lecture(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Lecture";
    }
}