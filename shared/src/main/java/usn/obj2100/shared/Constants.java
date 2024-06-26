package usn.obj2100.shared;

/**
 * A class for storing shared constants used for the server and client modules.
 * <p/>
 * Example usage:
 * {@snippet id = "ConstantsExample" lang = "java" group = "Constants":
 * 	import static usn.obj2100.shared.Constants.PORT;
 * 	System.out.println(PORT);
 *}
 *
 * @since 0.1
 * @created 2024-05-14
 */
public final class Constants
{
	private Constants() {}
	
	/* NETWORKING. */
	public static final int PORT = 8765;
	
	/* DATABASE. */
	public static final boolean CREATE_EXAMPLE_DATA = true;
}
