package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GorevliController extends Controller {

	@FXML
	Button add;
	@FXML
	Button remove;
	@FXML
	Button submit;
	@FXML
	Label label;
	@FXML
	Label warningLabel;
	@FXML
	Label processLabel;
	@FXML
	TextField textField;
	boolean submitBool;

	public void addButton(ActionEvent e) {
		add.setVisible(false);
		remove.setVisible(false);
		submit.setVisible(true);
		textField.setVisible(true);
		label.setText("Gorevli Adini Giriniz");
		submitBool = true;
	}

	public void removeButton(ActionEvent e) {
		add.setVisible(false);
		remove.setVisible(false);
		submit.setVisible(true);
		textField.setVisible(true);
		label.setText("Gorevli id'sini Giriniz");
		submitBool = false;

	}

	public void submit(ActionEvent e) {
		try {
			if (submitBool) {
				getManagement().insertGorevli(textField.getText());
				warningLabel.setText(textField.getText() + " eklendi.");
				processLabel.setText("Gorevli Ekleme");
			} else {
				getManagement().deleteGorevli(Integer.parseInt(textField.getText()));
				warningLabel.setText("id: " + textField.getText() + " kaldirildi.");
				processLabel.setText("Gorevli Kaldirma");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
