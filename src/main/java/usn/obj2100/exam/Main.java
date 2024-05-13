package usn.obj2100.exam;

import javafx.application.Application;
import javafx.stage.Stage;
import usn.obj2100.exam.database.DBManager;

import java.io.IOException;


public class Main extends Application
{
	private DBManager dbManager;
	
	@Override
	public void start(Stage stage) throws IOException
	{
		dbManager = new DBManager();
		
		//stage.setTitle("Hello!");
		//stage.setScene(scene);
		//stage.show();
	}
	
	public static void main(String[] args)
	{
		launch();
	}
}
