/**
 * Exception when the course is full and not allowed to register
 */
public class ExceptionFull extends Exception {
    public ExceptionFull(){
        super("The course is full!");
    }
}
