/**
 * Expception when an entered number is not valid
 */
public class ExceptionInvalidNumber extends Exception {
	public ExceptionInvalidNumber() {
		super("The entered number is not valid!");
	}
	public ExceptionInvalidNumber(String object) {
		super("The number of " + object + " entered is not valid!");
	}
}
