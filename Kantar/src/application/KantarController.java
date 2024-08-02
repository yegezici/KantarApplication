package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KantarController extends Controller {
	@FXML
	TextField aracPlakaTf;
	@FXML
	TextField doluYukTf;
	@FXML
	TextField bosYukTf;
	@FXML
	TextField urunIdTf;
	@FXML
	TextField depoIdTf;
	@FXML
	TextField gorevliIdTf;
	@FXML
	TextField isEmriTf;
	@FXML
	Label kantarWarningLabel;
	@FXML
	Label gorevliName;

	String aracPlaka;
	String doluYuk;
	String bosYuk;
	String urunId;
	String depoId;
	String gorevliId;
	String isEmri;
	int indexOfWarningLabel = 1;

	public void submit(ActionEvent e) {
		int depo_id = 0;
		try {
			gorevliId = gorevliIdTf.getText();
			depoId = depoIdTf.getText();
			urunId = urunIdTf.getText();
			bosYuk = bosYukTf.getText();
			doluYuk = doluYukTf.getText();
			aracPlaka = aracPlakaTf.getText();
			isEmri = isEmriTf.getText();

			if (aracPlaka.length() != 0 || bosYuk.length() != 0 || depoId.length() != 0 || doluYuk.length() != 0
					|| gorevliId.length() != 0 || isEmri.length() != 0 || urunId.length() != 0) {
				double dolu_yuk = Double.parseDouble(doluYuk);
				double bos_yuk = Double.parseDouble(bosYuk);
				depo_id = Integer.parseInt(depoId);
				int is_emri = Integer.parseInt(isEmri);
				int urun_id = Integer.parseInt(urunId);
				int gorevli_id = Integer.parseInt(gorevliId);
				boolean check = false;
				if (!checkExistsDepo(depo_id)) {
					kantarWarningLabel.setText("There is no depo with id: " + depo_id);
					check = true;
				}
				if (!checkExistsGorevli(gorevli_id)) {
					kantarWarningLabel.setText("There is no gorevli with id: " + gorevli_id);
					check = true;
				}
				if (!checkExistsUrun(urun_id)) {
					kantarWarningLabel.setText("There is no urun with id: " + urun_id);
					check = true;
				}
				if (!check)
					getManagement().insertKantar(aracPlaka, dolu_yuk, bos_yuk, depo_id, urun_id, LocalDateTime.now(),
							gorevli_id, is_emri);
			} else {
				kantarWarningLabel.setText(indexOfWarningLabel++ + ". Lutfen her satiri doldurunuz");
			}
		} catch (Exception e1) {
			int kapasite = getManagement().getKapasite(depo_id);
			int doluMiktar = getManagement().getDoluMiktar(depo_id);
			kantarWarningLabel.setText("Depo dolu.\nBu depoya eklenebilecek Miktar: " + (kapasite - doluMiktar)
					+ ".\nLutfen baska depo seciniz.");
			System.out.println(e1);
		}
	}

	public boolean checkExistsGorevli(int id) {
		boolean rval = false;
		String query = "SELECT gorevli_id from Gorevliler";
		try {
			ResultSet result = getManagement().getStatement().executeQuery(query);
			while (result.next()) {
				if (result.getInt(1) == id)
					rval = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rval;
	}

	public boolean checkExistsDepo(int id) {
		boolean rval = false;
		String query = "SELECT depo_id from Depolar";
		try {
			ResultSet result = getManagement().getStatement().executeQuery(query);
			while (result.next()) {
				if (result.getInt(1) == id)
					rval = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rval;
	}

	public boolean checkExistsUrun(int id) {
		boolean rval = false;
		String query = "SELECT urun_id from Urunler";
		try {
			ResultSet result = getManagement().getStatement().executeQuery(query);
			while (result.next()) {
				if (result.getInt(1) == id)
					rval = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rval;
	}

}
