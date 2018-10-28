/**
 * Exception class for duplicate objects
 */
public class ExceptionDuplicate extends Exception{
    public ExceptionDuplicate(String object){
        super( object + " is duplicated!");
    }
}
