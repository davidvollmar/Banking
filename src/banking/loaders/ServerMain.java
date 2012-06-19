package banking.loaders;

import banking.customer.model.ISession;
import banking.customer.model.Session;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 15, 2012
 */
public class ServerMain {

	/**
	 * Server launcher
	 *
	 * @param args not required / used.
	 */
	public static void main(String[] args) {
		new ServerMain();
	}

	/**
	 * Launches a new instance of this server, providing a RMI server.
	 */
	public ServerMain() {		
		
		
		try {
			System.out.println("Starting Server.");
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			ISession session = new Session();
			Naming.rebind("session", session);
			System.out.println("Server started succesfully");
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Server - Client connection ended unexpectedly");
			Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
