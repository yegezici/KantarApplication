package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepoController extends Controller {

	@FXML
	Button add;
	@FXML
	Button remove;
	@FXML
	Button submit;
	@FXML
	Label label;
	@FXML
	TextField textField;
	boolean submitBool;

	public void addButton(ActionEvent e) {
		add.setVisible(false);
		remove.setVisible(false);
		submit.setVisible(true);
		textField.setVisible(true);
		label.setText("Kapasiteyi Giriniz");
		submitBool = true;
	}

	public void removeButton(ActionEvent e) {
		add.setVisible(false);
		remove.setVisible(false);
		submit.setVisible(true);
		textField.setVisible(true);
		label.setText("ID giriniz");
		submitBool = false;
	}

	public void submit(ActionEvent e) {
		try {
			if (submitBool) {
				getManagement().insertDepo(Integer.parseInt(textField.getText()));
			} else {
				getManagement().deleteDepo(Integer.parseInt(textField.getText()));
			}
		} catch (Exception e1) {
			System.out.println(123213);
			e1.printStackTrace();
			
		}
	}

}
