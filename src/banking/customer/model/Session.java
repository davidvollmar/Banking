package banking.customer.model;

import banking.bank.Bank;
import banking.bank.Transaction;
import banking.loaders.CustomerMain;
import exceptions.NotAuthenticatedException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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

	public Session(Bank bank) throws RemoteException {
		if (bank == null) {
			throw new NullPointerException();
		}
		this.bank = bank;
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
			this.bankAccount = bank.getBankAccount(username);
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
	 * @return @throws RemoteException
	 * @throws NotAuthenticatedException if not authenticated. Run login()
	 * first. first.
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

	/**
	 * Transfers the {@code amount} to the {@code accountNumber}. 
	 * @param accountNumber
	 * @param amount
	 * @return Returns true if processed succesfully, false if otherwise.
	 * @throws RemoteException
	 * @throws NotAuthenticatedException 
	 */
	@Override
	public synchronized boolean transfer(int accountNumber, long amount) throws RemoteException, NotAuthenticatedException {
		if (!isAuthenticated()) {
			throw new NotAuthenticatedException();
		}
		if (bankAccount.canSubtract(amount)) {
			if (bank.hasBankAccount(accountNumber)) {
				BankAccount to = bank.getBankAccount(accountNumber);
				if (to == null) {
					return false;

				}
				to.add(amount);
				return bankAccount.subtract(amount);
			}else{
				return bank.transferToForeignAccount(accountNumber, amount, bankAccount) && bankAccount.subtract(amount);
			}
		}
		return false;
	}

	@Override
	public boolean isAuthenticated() throws RemoteException {
		return isAuthenticated;
	}

	@Override
	public int getAccountNumber() throws RemoteException, NotAuthenticatedException {
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
		ArrayList<Transaction> t = bank.getLatestTransactions(10, bankAccount.getAccountNumber());
		String ret[][] = new String[4][10];
		if (t == null || t.isEmpty()) {
			return null;
		}
		for (int i = 0; i < t.size(); i++) {
			ret[i] = t.get(i).toStringArray();
		}
		return ret;
	}
}
