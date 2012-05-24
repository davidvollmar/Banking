package exceptions;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 29, 2012
 */
public class NotAuthenticatedException extends Exception{

	public NotAuthenticatedException() {
		super();
	}

	public NotAuthenticatedException(String message) {
		super(message);
	}

}
