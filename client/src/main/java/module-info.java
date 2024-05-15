module client {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.controlsfx.controls;
	requires shared;
	
	opens usn.obj2100.client to javafx.fxml;
	exports usn.obj2100.client;
}
