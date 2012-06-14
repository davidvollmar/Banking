package banking.customer.model;

import exceptions.NotAuthenticatedException;
import banking.bank.Bank;
import banking.loaders.CustomerMain;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates a new Session as a facade for the bank transaction system.
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 15, 2012
 */
public class Session extends UnicastRemoteObject implements ISession {

	private int sessionID = -1;
	private int bankAccountID = -1;
	private boolean isAuthenticated = false;
	private transient BankAccount bankAccount = null;
	private transient Bank bank = null;

	public Session() throws RemoteException {
	}

	/**
	 * Log the user in with the credentials
	 *
	 * @param username name of the account
	 * @param notEncryptedPassword password of the account
	 * @return if the login is successful with valid username and password
	 * @throws RemoteException on connection failure
	 * @throws IllegalArgumentException if username or password is empty
	 */
	@Override
	public boolean login(String username, String notEncryptedPassword) throws RemoteException {
		if (username.isEmpty() || notEncryptedPassword.isEmpty()) {
			throw new IllegalArgumentException();
		}
		System.out.println("Client tries to log in");
		if (bank.authenticate(username, notEncryptedPassword)) {
			System.out.println("Auth: OK!");
			isAuthenticated = true;
			this.bankAccount = bank.getBankAccount("test");
			return true;
		} else {
			System.out.println("Auth: FAILED");
			isAuthenticated = false;
			return false;
		}
	}

	@Override
	public void logout() throws RemoteException {
		if (isAuthenticated()) {
			System.out.println("Client logout.");
		} else {
			System.out.println("Bad state! Client was not even logged in on logout!");
		}
		bankAccount = null;
		isAuthenticated = false;
	}

	/**
	 *
	 * @param bank
	 * @throws RemoteException
	 */
	@Override
	public void setBank(Bank bank) throws RemoteException {
		//TODO replace by central bank
		if (bank == null) {
			throw new NullPointerException();
		}
		this.bank = bank;
	}

	/**
	 *
	 * @return @throws RemoteException
	 * @throws NotAuthenticatedException if not authenticated. Run login() first.
	 * first.
	 */
	@Override
	public long getSaldo() throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		return bankAccount.getSaldo();
	}

	/**
	 *
	 * @param name
	 * @param place
	 * @param notEncryptedPassword
	 * @return
	 * @throws RemoteException
	 * @throws IllegalArgumentException if one of the parameters is empty.
	 */
	@Override
	public boolean createNewAccount(String name, String place, String notEncryptedPassword) throws RemoteException {
		if (name.isEmpty() || place.isEmpty() || notEncryptedPassword.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (CustomerMain.DEBUG) {
			Logger.getGlobal().log(Level.INFO, "Account with name: {0} added.", name);
		}
		return bank.addAccount(name, place, notEncryptedPassword);
	}

	@Override
	public synchronized boolean transfer(int accountNumber, long amount) throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		if (bankAccount.canSubtract(amount)) {
			//todo find right bank
			BankAccount to = bank.getBankAccount(accountNumber);
			if (to == null) {
				return false;
			}
			to.add(amount);
			return bankAccount.subtract(amount);
		}
		return false;
	}

	@Override
	public boolean isAuthenticated() throws RemoteException {
		return isAuthenticated;
	}

	@Override
	public int getAccountNumer() throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		return bankAccount.getAccountNumber();
	}

	@Override
	public String getAccountOwner() throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		return bankAccount.getName();
	}

	@Override
	public String getAccountPlace() throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		return bankAccount.getPlace();
	}

	@Override
	public long getLimit() throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		return bankAccount.getLimit();
	}

	@Override
	public String[][] getLatestTransactions() throws RemoteException, NotAuthenticatedException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
