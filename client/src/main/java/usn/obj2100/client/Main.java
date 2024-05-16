package usn.obj2100.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
		  Client serverConnection = new Client();
        BorderPane root = new BorderPane();
		  ClientController clientController = new ClientController(root, serverConnection);
		  clientController.initHandlers();
		  Scene scene = new Scene(clientController.getRoot(), 800, 600);
		  primaryStage.setScene(scene);
		  primaryStage.setTitle("Inventarstyringssystem");
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
