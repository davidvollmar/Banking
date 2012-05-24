package banking.bank;

import banking.customer.model.BankAccount;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 */
public class BankTest {

	/**
	 * Test of authenticate method, of class Bank.
	 */
	@Test
	public void testAuthenticate() {
		System.out.println("authenticate");
		String username = "username";
		String password = "password";
		String wrongUsername = "wrong username";
		String wrongPassword = "wrong password";
		Bank instance = new Bank("testbank");
		instance.addAccount(username, "location", password);

		assertTrue(instance.authenticate(username, password));

		assertFalse(instance.authenticate(username, wrongPassword));
		assertFalse(instance.authenticate(wrongUsername, password));
		assertFalse(instance.authenticate(wrongUsername, wrongPassword));
		assertFalse(instance.authenticate("", password));
		assertFalse(instance.authenticate(username, ""));
	}

	/**
	 * Test of getBankAccount method, of class Bank.
	 */
	@Test
	public void testGetBankAccount() {
		System.out.println("getBankAccount");
		String name = "name";
		String place = "place";
		String password = "password";
		Bank bank = new Bank("");
		bank.addAccount(name, "place", "password");
		BankAccount account = bank.getBankAccount(name);

		assertEquals(name, account.getName());
		assertEquals(place, account.getPlace());
		assertEquals(password, account.getPassword());
	}

	/**
	 * Test of hasBankAccount method, of class Bank.
	 */
	@Test
	public void testHasBankAccount_String() {
		System.out.println("hasBankAccount");
		String name = "name";
		String place = "place";
		String password = "password";
		Bank instance = new Bank("");
		instance.addAccount(name, place, password);
		assertTrue(instance.hasBankAccount(name));
		//bank is a {@code new}, so the bank account with name {@code name} is the only one.
		assertFalse(instance.hasBankAccount(name + "wrong"));
	}

	/**
	 * Test of hasBankAccount method, of class Bank.
	 */
	@Test
	public void testHasBankAccount_int() {
		System.out.println("hasBankAccount");
		String name = "name";
		Bank instance = new Bank("");
		instance.addAccount(name, "place", "password");
		int id = instance.getAccountNumber(name);
		assertTrue(instance.hasBankAccount(id));
		assertFalse(instance.hasBankAccount(id + 1));
		assertFalse(instance.hasBankAccount(-1));
	}

	/**
	 * Test of addAccount method, of class Bank.
	 */
	@Test
	public void testAddAccount() {
		System.out.println("addAccount");
		Bank bank = new Bank("");
		assertTrue(bank.addAccount("name", "place", "password"));
		assertFalse(bank.addAccount("name", "place", ""));
		assertFalse(bank.addAccount("name", "", "password"));
		assertFalse(bank.addAccount("", "place", "password"));
		assertFalse(bank.addAccount("", "", ""));
	}
}
