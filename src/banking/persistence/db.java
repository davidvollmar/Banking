package banking.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since May 25, 2012
 */
public class db {

	private Connection conn = null;

	public void createTable1() {
		createTables();
	}

	private db() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
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
