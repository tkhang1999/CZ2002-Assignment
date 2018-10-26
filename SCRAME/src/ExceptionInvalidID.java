/**
 * Exception when an ID is not valid
 */
public class ExceptionInvalidID extends Exception {
    public ExceptionInvalidID(String object){
        super("The ID of " + object + " entered is not valid!");
    }
}

