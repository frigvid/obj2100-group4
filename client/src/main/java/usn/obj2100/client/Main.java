package usn.obj2100.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Hovedklassen for JavaFX-applikasjonen som fungerer som inngangspunktet for Inventarstyringssystemet.
 * Denne klassen initialiserer og viser hovedvinduet og setter opp grunnleggende applikasjonskonfigurasjon.
 */
public class Main extends Application {
	/**
	 * Starter og setter opp hovedscenen for applikasjonen.
	 * Denne metoden initialiserer nettverkstilkoblingen, setter opp hovedkontrolleren,
	 * og oppretter hovedscenen som brukes gjennom applikasjonen.
	 *
	 * @param primaryStage Hovedscenen som JavaFX oppretter som grunnlag for applikasjonen.
	 * @throws Exception Håndterer unntak som kan oppstå under oppstart.
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
		 // Oppretter en ny klientforbindelse til serveren.
		 Client serverConnection = new Client();

		 // Setter opp rotkomponenten i brukergrensesnittet.
		 BorderPane root = new BorderPane();

		 // Initialiserer hovedkontrolleren for klienten.
		 ClientController clientController = new ClientController(root, serverConnection);

		 // Initialiserer hendelseshåndterere.
		 clientController.initHandlers();

		 // Oppretter en ny scene med angitt bredde og høyde.
		 Scene scene = new Scene(clientController.getRoot(), 800, 600);

		 // Setter scenen for primærstadiet (hovedvinduet).
		 primaryStage.setScene(scene);

		 // Setter tittelen på primærstadiet.
		 primaryStage.setTitle("Inventarstyringssystem");

		 // Viser primærstadiet, som gjør applikasjonens GUI synlig for brukeren.
		 primaryStage.show();
    }
	/**
	 * Main-metoden som starter JavaFX-applikasjonen.
	 *
	 * @param args Argumenter gitt fra kommandolinjen.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
