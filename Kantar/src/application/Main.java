package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Kantar Yonetimi");
			Parent root = FXMLLoader.load(getClass().getResource("MainPane.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			Controller.setManagement(new Management());
			Controller.setStage(primaryStage);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
