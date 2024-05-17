module Codsoft_Assignment1_Q3 {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
