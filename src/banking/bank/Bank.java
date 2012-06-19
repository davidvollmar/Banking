package banking.bank;

import banking.customer.model.BankAccount;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 29, 2012
 */
public class Bank {

	private ArrayList<BankAccount> bankAccounts;
	private ArrayList<Transaction> transactions;
	private String name;
	private String prefix;

	/**
	 *
	 */
	public Bank(String name, String prefix) {
		this.name = name;
		this.prefix = prefix;
		bankAccounts = new ArrayList<>();
		transactions = new ArrayList<>();
		//bankAccounts.add(getBankStubAccount());
	}

	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String username, String password) {
		for (BankAccount b : bankAccounts) {
			if (b.getName().equals(username)) {
				if (b.getPassword().equals(password)) {
					return true;
				} else {
					break;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the bank account with this {@code name}.
	 *
	 * @param name
	 * @return the BankAccount with {@code name}, {@code null} if non-existent.
	 */
	public BankAccount getBankAccount(String name) {
		//Not efficient, but it works.
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getName().equals(name)) {
				return bankAccounts.get(i);
			}
		}
		return null;
	}

	public BankAccount getBankAccount(int accountNumber) {
		//Not efficient, but it works.
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getAccountNumber() == accountNumber) {
				return bankAccounts.get(i);
			}
		}
		return null;
	}

	public boolean hasBankAccount(String name) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasBankAccount(int id) {
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getAccountNumber() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the account number from the bank account name. Returns -1 if the
	 * bank account does not exist.
	 *
	 * @param bankAccountName
	 * @return
	 */
	public int getAccountNumber(String bankAccountName) {
		BankAccount b = getBankAccount(bankAccountName);
		if (b == null) {
			return -1;
		}
		return b.getAccountNumber();
	}

	/**
	 * For testing purposes.
	 *
	 * @return
	 */
	public BankAccount getBankStubAccount() {
		return new BankAccount(1, "test", "Eindhoven", "testpassword");
	}

	/**
	 *
	 * @param name
	 * @param place
	 * @param password
	 * @return
	 */
	public synchronized boolean addAccount(String name, String place, String password) {
		if (name.isEmpty() || place.isEmpty() || password.isEmpty()) {
			return false;
		}
		BankAccount b = new BankAccount(bankAccounts.size(), name, place, password);
		return bankAccounts.add(b);
	}

	public boolean addTransaction(int accountTo, int accountFrom, Date date, long amount) {
		if (date == null) {
			throw new NullPointerException();
		}
		return transactions.add(new Transaction(accountTo, accountFrom, date, amount));
	}

		public ArrayList<Transaction> getTransactions() {
		return (ArrayList<Transaction>) transactions.clone();
	}

	//todo: document + test
	public ArrayList<Transaction> getLatestTransactions(int amountOfTransactions, int bankAccountNumber) {
		ArrayList<Transaction> filteredList = filterTransactions(bankAccountNumber);
		if (amountOfTransactions < 1 || filteredList.isEmpty()) {
			return null;
		} else {
			int start = (filteredList.size() < amountOfTransactions) ? 0 : filteredList.size() - amountOfTransactions;
			return new ArrayList(filteredList.subList(start, filteredList.size() - 1));
		}
	}

	private ArrayList<Transaction> filterTransactions(int accountNumber){
		ArrayList<Transaction> filteredList = new ArrayList<>();
		for(Transaction t : transactions){
			if(t.getAccountFrom() == accountNumber || t.getAccountTo() == accountNumber){
				filteredList.add(t);
			}
		}
		return filteredList;
	}
}
