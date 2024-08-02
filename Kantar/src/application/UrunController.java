package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UrunController extends Controller{
	
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
		label.setText("Urun adini giriniz");
		submitBool = true;
	}

	public void removeButton(ActionEvent e) {
		add.setVisible(false);
		remove.setVisible(false);
		submit.setVisible(true);
		textField.setVisible(true);
		label.setText("Urun id'sini giriniz");
		submitBool = false;
	}
	public void submit(ActionEvent e) {
		try {
			if(submitBool) {
				getManagement().insertUrun(textField.getText());
			}else {
				getManagement().deleteUrun(Integer.parseInt(textField.getText()));
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}		
	}


}
