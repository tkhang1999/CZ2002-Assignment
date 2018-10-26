/**
 * Exception when an object is not fully set
 */
public class ExceptionNotSet extends Exception {
	public ExceptionNotSet(String object) {
		super(object + " is not fully set!");
	}
}
