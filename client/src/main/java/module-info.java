module usn.obj2100 {
    requires javafx.controls;
    requires javafx.fxml;
	 requires java.sql;
	 
    opens usn.obj2100 to javafx.fxml;
    exports usn.obj2100;
}
