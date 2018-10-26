/**
 * Exception when an object is not found
 */
public class ExceptionNotFound extends Exception {
	public ExceptionNotFound(String object) {
		super("The " + object + " is not found!");
	}
}
