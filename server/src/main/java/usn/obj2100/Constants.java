package usn.obj2100;

/**
 * A class for storing constants used in the application.
 * <p/>
 * Example usage:
 * {@snippet id = "ConstantsExample" lang = "java" group = "Constants":
 * 	import static com.frigvid.jman.Constants.MENU_BUTTON_STYLE;
 * 	Constants.MENU_BUTTON_STYLE
 * }
 *
 * @since 0.1
 * @created 2024-02-14
 */
public final class Constants
{
	private Constants() {}
	
	/* DATABASE. */
	public static final String DB_NAME = "database.sqlite";
	public static final String DB_URL = "jdbc:sqlite:" + DB_NAME;
	
	/* NETWORKING. */
	public static final int PORT = 63821;
}
