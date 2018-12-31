/**
 * 
 */
package sm.coding.java.threading.immutable;

/**
 * @author shahzadmughal8410
 *
 */
public final class ImmutableObject {

	private final String message;
	
	
	public ImmutableObject(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImmutableObject immutable = new ImmutableObject("This message cannot be changed!");
		
		System.out.println("Immutable message = "+immutable.getMessage());
		

	}

}
