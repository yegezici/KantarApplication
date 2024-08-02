package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TablePaneController extends Controller {
	@FXML
	Button depo;
	@FXML
	Button kantar;
	@FXML
	Button gorevli;
	@FXML
	Button urun;
	@FXML
	TextArea table;

	public void depoButton(ActionEvent e) {
		ArrayList<ArrayList<String>> list = getManagement().printDepolarTable();
		if (list.size() == 0) {
			table.setText(" ");
		} else {
			int length1 = list.size(), length2 = list.get(0).size();
			String f = "ID              |KAPASITE        |DOLULUK         |\n";
			int lOF = f.length();
			for (int i = 0; i < lOF - 1; i++)
				f += "-";
			f += "\n";
			for (int i = 0; i < length1; i++) {
				for (int j = 0; j < length2; j++)
					f += String.format("%-15s |", list.get(i).get(j));
				f += "\n";

			}
			table.setText(f);
		}
	}

	public void gorevliButton(ActionEvent e) {
		ArrayList<ArrayList<String>> list = getManagement().printGorevliTable();
		if (list.size() == 0) {
			table.setText(" ");
		} else {
			int length1 = list.size(), length2 = list.get(0).size();
			String f = "ID             |ISIM           |\n";
			int lOF = f.length();
			for (int i = 0; i < lOF - 1; i++)
				f += "-";
			f += "\n";
			for (int i = 0; i < length1; i++) {
				for (int j = 0; j < length2; j++) {
					int tempLength = list.get(i).get(j).length();
					f += list.get(i).get(j);
					for (int k = 0; k < 15 - tempLength; k++)
						f += " ";
					f += "|";
				}
				f += "\n";
			}
			table.setText(f);
		}
	}

	public void urunButton(ActionEvent e) {
		ArrayList<ArrayList<String>> list = getManagement().printUrunTable();
		if (list.size() == 0) {
			table.setText(" ");
		} else {
			int length1 = list.size(), length2 = list.get(0).size();
			String f = "ID        |URUN ADI  |\n";
			int lOF = f.length();
			for (int i = 0; i < lOF - 1; i++)
				f += "-";
			f += "\n";
			for (int i = 0; i < length1; i++) {
				for (int j = 0; j < length2; j++) {
					int tempLength = list.get(i).get(j).length();
					f += list.get(i).get(j);
					for (int k = 0; k < 10 - tempLength; k++)
						f += " ";
					f += "|";
				}
				f += "\n";
			}
			table.setText(f);
		}
	}

	public void kantarButton(ActionEvent e) {
		ArrayList<ArrayList<String>> list = getManagement().printKantarTable();
		if (list.size() == 0) {
			table.setText(" ");
		} else {
			int length1 = list.size(), length2 = list.get(0).size();
			String f = "ID        |PLAKA     |DOLU YUK  |BOS YUK   |URUN ID   |DEPO ID   |ISLEM SAATI        |GOREVLI ID|IS EMRI   |\n";
			;
			int lOF = f.length();
			for (int i = 0; i < lOF -1; i++)
				f += "-";
			f += "\n";
			for (int i = 0; i < length1; i++) {
				for (int j = 0; j < length2; j++) {
					int tempLength = list.get(i).get(j).length();
					f += list.get(i).get(j);
					for (int k = 0; k < 10 - tempLength; k++)
						f += " ";
					f += "|";
				}
				f += "\n";
			}
			table.setText(f);
		}

	}

	public void toplamUrun(ActionEvent e) {
		try {
			ResultSet result = getManagement().getStatement().executeQuery(
					"SELECT  u.urun_adi,SUM(k.dolu_yuk - k.bos_yuk) AS difference FROM Kantar k INNER JOIN Urunler u ON k.urun_id = u.urun_id GROUP BY u.urun_adi;");
			String t = "";
			while (result.next()) {
				t += String.format("%-15s : %-15s\n", result.getString(1), result.getString(2));
			}
			table.setText(t);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
