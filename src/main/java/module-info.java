module usn.obj2100.exam {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	
	opens usn.obj2100.exam to javafx.fxml;
	exports usn.obj2100.exam;
}
