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
	// "user.dir" corresponds to IntelliJ's $PROJECT_DIR$ variable.
	public static final String DB_PATH = System.getProperty("user.dir") + "/database.sqlite";
	public static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
	
	/* NETWORKING. */
	public static final int PORT = 63821;
}
