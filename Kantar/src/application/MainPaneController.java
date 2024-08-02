package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainPaneController extends Controller {

	public void kantarPaneButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("KantarPane.fxml"));
			Scene scene = new Scene(root);
			super.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void gorevliPaneButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("InstructorPane.fxml"));
			Scene scene = new Scene(root);
			super.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void urunPaneButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("UrunPane.fxml"));
			Scene scene = new Scene(root);
			super.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void depoPaneButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("DepoPane.fxml"));
			Scene scene = new Scene(root);
			super.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void tabloPaneButton(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("TabloPane.fxml"));
			Scene scene = new Scene(root);
			super.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
