package banking.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Not used at the moment, not needed for this POC.
 * @author David Vollmar <david@vollmar.nl>
 * @since May 25, 2012
 */
public class CentralBankDatabase {

	private Connection conn = null;

	private CentralBankDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(CentralBankDatabase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean createTables() {
		boolean succes = false;
		Statement s;
		try {
			s = conn.createStatement();
			s.executeQuery("CREATE TABLE 'accounts' (accountnumber, name, place, password, limit, saldo);");
			s.executeQuery("CREATE TABLE 'transactions' (time, amount, accountnumberfrom, accountnumberto);");
			s.close();
			succes = true;
		} catch (SQLException e) {
			succes = false;
		} finally {
		}
		return succes;
	}
}
