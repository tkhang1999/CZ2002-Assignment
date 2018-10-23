
public class Laboratory extends Session {

    //CONSTRUCTOR
    public Laboratory(int ID, int capacity){
        super(ID,capacity);     //using Session's constructor
        this.name = "Laboratory";
    }
}