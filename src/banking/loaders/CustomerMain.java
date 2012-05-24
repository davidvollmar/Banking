package banking.loaders;

import banking.customer.model.ISession;
import banking.customer.view.LoginView;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 */
public class CustomerMain {

	public static boolean DEBUG = true;
	private static String rmiUrl = "rmi://127.0.0.1:1099/session";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			ISession session = (ISession) Naming.lookup(rmiUrl);

			System.out.println("Connected to server.");

			LoginView l = new LoginView(session);
			l.setVisible(true);
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			System.err.println("Connection was terminaten unexpectedly, please make sure the server is running.");
			Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
