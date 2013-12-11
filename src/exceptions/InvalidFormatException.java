package exceptions;

/**
 *
 * @author rachelmills
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super("Invalid currency format: " + message);
    }
}
