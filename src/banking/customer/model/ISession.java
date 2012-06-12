package banking.customer.model;

import exceptions.NotAuthenticatedException;
import banking.bank.Bank;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the session facade.
 * @author David Vollmar <david@vollmar.nl>
 */
public interface ISession extends Remote{

	public boolean login(String username, String password) throws RemoteException;
	public void logout() throws RemoteException;

	public int getSaldo() throws RemoteException, NotAuthenticatedException;

	public void setBank(Bank bank) throws RemoteException;

	public boolean createNewAccount(String name, String place, String password) throws RemoteException;
	public boolean transfer(int accountNumer, int amount) throws RemoteException, NotAuthenticatedException;
	public boolean isAuthenticated() throws RemoteException, NotAuthenticatedException;
	public int getAccountNumer() throws RemoteException, NotAuthenticatedException;
	public String getAccountOwner() throws RemoteException, NotAuthenticatedException;
	public String getAccountPlace() throws RemoteException, NotAuthenticatedException;
	public int getLimit() throws RemoteException, NotAuthenticatedException;
	public String[][] getLatestTransactions() throws RemoteException, NotAuthenticatedException;
}
