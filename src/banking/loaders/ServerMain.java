package banking.loaders;

import banking.bank.Bank;
import banking.customer.model.ISession;
import banking.customer.model.Session;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 15, 2012
 */
public class ServerMain {

	private ArrayList<Bank> banks = null;

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
		Bank bank = new Bank("ING","ING");
		
		try {
			System.out.println("Starting Server.");
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			ISession session = new Session();
			session.setBank(bank);
			Naming.rebind("session", session);
			System.out.println("Server started succesfully");
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Server - Client connection ended unexpectedly");
			Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
