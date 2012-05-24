package banking.bank;

import banking.customer.model.BankAccount;
import java.util.ArrayList;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 29, 2012
 */
public class Bank {

	private ArrayList<BankAccount> bankAccounts;
	private String name;

	/**
	 *
	 */
	public Bank(String name) {
		this.name = name;
		bankAccounts = new ArrayList<>();
		//bankAccounts.add(getBankStubAccount());
	}

	public String getName() {
		return name;
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
	public boolean addAccount(String name, String place, String password) {
		if (name.isEmpty() || place.isEmpty() || password.isEmpty()) {
			return false;
		}
		BankAccount b = new BankAccount(bankAccounts.size(), name, place, password);
		return bankAccounts.add(b);
	}
}
