
package exception;

// TODO: Auto-generated Javadoc
/**
 * The Class UnknownPlayerException.
 */
public class UnknownPlayerException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new unknown player exception.
	 *
	 * @param name the name
	 */
	public UnknownPlayerException(String name) {
		super("Unknown player with name: "+name);
	}
	
}
