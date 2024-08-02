package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class  Controller {
	private static Stage stage;
	private static Management management;
	

	public void turnBackButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainPane.fxml"));
			Scene scene = new Scene(root);
			Controller.stage.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static Management getManagement() {
		return management;
	}

	public static void setStage(Stage stage) {
		Controller.stage = stage;
	}
	public static void setManagement(Management management) {
		Controller.management = management;
	}


}
