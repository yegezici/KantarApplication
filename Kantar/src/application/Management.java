package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Management {
	private String url = "jdbc:mysql://localhost:3306/Stok";
	private String userName = "root";
	private String password = "password";
	private Connection connection;
	private Statement statement;

	public Management() {
		try {
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void insertGorevli(String gorevli_ismi) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call insertGorevli(?)}");
		myStatement.setString(1, gorevli_ismi);
		myStatement.execute();
		System.out.println(gorevli_ismi + " is successfully added");
	}

	public void deleteGorevli(int gorevli_id) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call deleteGorevli(?)}");
		myStatement.setInt(1, gorevli_id);
		myStatement.execute();
		System.out.println("id: " + gorevli_id + " is successfully removed");
	}

	public void insertUrun(String urun_ismi) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call insertUrun(?)}");
		myStatement.setString(1, urun_ismi);
		myStatement.execute();
		System.out.println(urun_ismi + " is successfully added");
	}

	public void deleteUrun(int urun_id) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call deleteUrun(?)}");
		myStatement.setInt(1, urun_id);
		myStatement.execute();
		System.out.println("id: " + urun_id + " is successfully removed");
	}

	public void insertDepo(int kapasite) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call insertDepo(?)}");
		myStatement.setInt(1, kapasite);
		myStatement.execute();
		System.out.println(kapasite + " is successfully added");
	}

	public void deleteDepo(int depo_id) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call deleteDepo(?)}");
		myStatement.setInt(1, depo_id);
		myStatement.execute();
		System.out.println("id: " + depo_id + " is successfully removed");
	}

	public void insertKantar(String aracPlaka, double dolu_yuk, double bos_yuk, int urun_id, int depo_id,
			LocalDateTime islem_saati, int gorevli_id, int is_emri) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call insertKantar(?,?,?,?,?,?,?,?)}");
		myStatement.setString(1, aracPlaka);
		myStatement.setDouble(2, dolu_yuk);
		myStatement.setDouble(3, bos_yuk);
		myStatement.setInt(4, urun_id);
		myStatement.setInt(5, depo_id);
		myStatement.setTimestamp(6, Timestamp.valueOf(islem_saati));
		myStatement.setInt(7, gorevli_id);
		myStatement.setInt(8, is_emri);
		myStatement.execute();
		System.out.println("id: " + 8 + " is successfully inserted");
	}

	public void deleteKantar(int orderNum) throws SQLException {
		CallableStatement myStatement = connection.prepareCall("{call deleteKantar(?)}");
		myStatement.setInt(1, orderNum);
		myStatement.execute();
	}

	public int getKapasite(int depoId) {
		try {
			String sql = "{ ? = CALL GetKapasite(?) }";
			CallableStatement myStatement = connection.prepareCall(sql);
			myStatement.registerOutParameter(1, Types.INTEGER);
			myStatement.setInt(2, depoId);
			myStatement.execute();
			return myStatement.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public int getDoluMiktar(int depoId) {
		try {
			String sql = "{ ? = CALL GetDoluMiktar(?) }";
			CallableStatement myStatement = connection.prepareCall(sql);
			myStatement.registerOutParameter(1, Types.INTEGER);
			myStatement.setInt(2, depoId);
			myStatement.execute();
			return myStatement.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public Statement getStatement() {
		return statement;
	}

	public ArrayList<ArrayList<String>> printKantarTable() {
		String query = "SELECT * FROM Kantar";
		ResultSet result;
		ArrayList<ArrayList<String>> kantarTable = new ArrayList<ArrayList<String>>();
		try {
			result = statement.executeQuery(query);
			while (result.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i <= 9; i++)
					list.add(result.getString(i));
				kantarTable.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kantarTable;
	}

	public ArrayList<ArrayList<String>> printUrunTable() {
		String query = "SELECT * FROM Urunler";
		ResultSet result;
		ArrayList<ArrayList<String>> urunTable = new ArrayList<ArrayList<String>>();
		try {
			result = statement.executeQuery(query);
			while (result.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i <= 2; i++)
					list.add(result.getString(i));
				urunTable.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urunTable;
	}

	public ArrayList<ArrayList<String>> printGorevliTable() {
		String query = "SELECT * FROM Gorevliler";
		ResultSet result;
		ArrayList<ArrayList<String>> gorevliTable = new ArrayList<ArrayList<String>>();
		try {
			result = statement.executeQuery(query);
			while (result.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i <= 2; i++)
					list.add(result.getString(i));
				gorevliTable.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gorevliTable;
	}

	public ArrayList<ArrayList<String>> printDepolarTable() {
		String query = "SELECT * FROM Depolar";
		ResultSet result;
		ArrayList<ArrayList<String>> depoTable = new ArrayList<ArrayList<String>>();
		try {
			result = statement.executeQuery(query);
			while (result.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i <= 3; i++)
					list.add(result.getString(i));
				depoTable.add(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depoTable;
	}
}
