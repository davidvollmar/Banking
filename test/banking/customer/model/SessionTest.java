package banking.customer.model;

import banking.bank.Bank;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 */
public class SessionTest {

	/**
	 * Test of login method, of class Session.
	 */
	@Test
	public void testLogin() throws Exception {
		System.out.println("login");
		Bank b = new Bank("ING", "ING");
		b.addStubAccount();
		BankAccount bA = b.getBankStubAccount();
		String username = bA.getName();
		String notEncryptedPassword = bA.getPassword();
		Session instance = new Session(b);
		boolean expResult = true;
		boolean result = instance.login(username, notEncryptedPassword);
		assertEquals(expResult, result);
	}

	@Test
	public void testLoginFail() throws Exception {
		System.out.println("login");
		Bank b = new Bank("ING", "ING");
		b.addStubAccount();
		BankAccount bA = b.getBankStubAccount();
		String username = bA.getName();
		String notEncryptedPassword = bA.getPassword() + "FAIL";
		Session instance = new Session(b);
		boolean expResult = false;
		boolean result = instance.login(username, notEncryptedPassword);
		assertEquals(expResult, result);
	}

	/**
	 * Test of transfer method, of class Session.
	 */
	@Test
	public void testTransfer() throws Exception {
		System.out.println("transfer");
		int accountNumber = 0;
		int amount = 0;
		Bank b = new Bank("ING", "ING");
		Session instance = new Session(b);
		BankAccount bA = b.getBankStubAccount();
		b.addStubAccount();
		String username = bA.getName();
		String notEncryptedPassword = bA.getPassword();
		instance.login(bA.getName(), bA.getPassword());
		b.addAccount("luser1", "place", "password");
		int user1id = b.getAccountNumber("luser1");		
		boolean expResult = true;
		boolean result = instance.transfer(user1id, 100);
		assertEquals(expResult, result);		
	}
}
