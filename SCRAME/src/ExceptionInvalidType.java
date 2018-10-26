/**
 * Exception when the input type is invalid
 */
public class ExceptionInvalidType extends Exception {
    public ExceptionInvalidType(){
        super("The type of input is invalid!");
    }
    public ExceptionInvalidType(String object){
        super("The type of input is invalid, please enter " + object + "!");
    }
}
