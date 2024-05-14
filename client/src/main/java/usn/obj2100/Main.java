package usn.obj2100;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
		  ClientController clientController = new ClientController(root);
		 	Scene scene = new Scene(root, 800, 600);
		 	primaryStage.setScene(scene);
		 	primaryStage.setTitle("Inventarstyringssystem");
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
