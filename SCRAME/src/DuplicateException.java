/**
 * Exception class for duplicate objects
 */
public class DuplicateException extends Exception{
    public DuplicateException(String object){
        super( object + " is duplicated!");
    }
}
