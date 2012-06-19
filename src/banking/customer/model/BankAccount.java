package banking.customer.model;

import banking.bank.Transaction;
import java.util.ArrayList;
import java.util.Date;

/**
 * Bank Account class.
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Mar 29, 2012
 */
public class BankAccount {

	private long saldo;
	private int accountNumber;
	private String name;
	private String place;
	private String notEncryptedPassword;
	/**
	 * The negative limit for this bank account. If the limit is €-100, this
	 * will be -100.
	 */
	private long limit;	

	/**
	 * Create a new bank account.
	 *
	 * @param name
	 * @param place
	 * @param notEncryptedPassword
	 * @throws IllegalArgumentException if at least one of the parameters is
	 * empty.
	 */
	public BankAccount(int accountNumber, String name, String place, String notEncryptedPassword) {
		if (name.isEmpty() || place.isEmpty() || notEncryptedPassword.isEmpty()) {
			throw new IllegalArgumentException("Invalid parameter at bank account creation");
		}
		this.name = name;
		this.place = place;
		this.notEncryptedPassword = notEncryptedPassword;
		this.limit = -100;
		this.saldo = 0;
		this.accountNumber = accountNumber;		
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public long getSaldo() {
		return saldo;
	}

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public String getPassword() {
		return notEncryptedPassword;
	}

	/**
	 * add an amount to the saldo. If the amount is negative, the amount will
	 * not be subtracted.
	 *
	 * @param amount
	 */
	public void add(long amount) {
		if (amount > 0) {
			saldo += amount;
		}
	}

	/**
	 * subtract the {
	 *
	 * @param amount} from the bank account.
	 *
	 * @param amount is absolute, so subtracting 300 or -300 gives the same
	 * result
	 * @return boolean if the subtraction is possible.
	 */
	public boolean subtract(long amount) {
		if (amount < 0) {
			amount = -amount;
		}
		if (!canSubtract(amount)) {
			return false;
		} else {
			saldo -= amount;
			return true;
		}
	}

	public boolean canSubtract(long amount) {
		if (amount < 0) {
			amount = -amount;
		}
		return amount <= saldo - limit;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		if (limit > 0) {
			limit = -limit;
		}
		this.limit = limit;
	}




}
