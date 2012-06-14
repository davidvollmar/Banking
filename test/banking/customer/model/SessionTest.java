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
		String username = "";
		String notEncryptedPassword = "";
		Session instance = new Session();
		boolean expResult = false;
		boolean result = instance.login(username, notEncryptedPassword);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of logout method, of class Session.
	 */
	@Test
	public void testLogout() throws Exception {
		System.out.println("logout");
		Session instance = new Session();
		instance.logout();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setBank method, of class Session.
	 */
	@Test
	public void testSetBank() throws Exception {
		System.out.println("setBank");
		Bank bank = null;
		Session instance = new Session();
		instance.setBank(bank);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSaldo method, of class Session.
	 */
	@Test
	public void testGetSaldo() throws Exception {
		System.out.println("getSaldo");
		Session instance = new Session();
		long expResult = 0;
		long result = instance.getSaldo();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of createNewAccount method, of class Session.
	 */
	@Test
	public void testCreateNewAccount() throws Exception {
		System.out.println("createNewAccount");
		String name = "";
		String place = "";
		String notEncryptedPassword = "";
		Session instance = new Session();
		boolean expResult = false;
		boolean result = instance.createNewAccount(name, place, notEncryptedPassword);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of transfer method, of class Session.
	 */
	@Test
	public void testTransfer() throws Exception {
		System.out.println("transfer");
		int accountNumber = 0;
		int amount = 0;
		Session instance = new Session();
		boolean expResult = false;
		boolean result = instance.transfer(accountNumber, amount);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isAuthenticated method, of class Session.
	 */
	@Test
	public void testIsAuthenticated() throws Exception {
		System.out.println("isAuthenticated");
		Session instance = new Session();
		boolean expResult = false;
		boolean result = instance.isAuthenticated();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAccountNumer method, of class Session.
	 */
	@Test
	public void testGetAccountNumer() throws Exception {
		System.out.println("getAccountNumer");
		Session instance = new Session();
		int expResult = 0;
		int result = instance.getAccountNumer();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAccountOwner method, of class Session.
	 */
	@Test
	public void testGetAccountOwner() throws Exception {
		System.out.println("getAccountOwner");
		Session instance = new Session();
		String expResult = "";
		String result = instance.getAccountOwner();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAccountPlace method, of class Session.
	 */
	@Test
	public void testGetAccountPlace() throws Exception {
		System.out.println("getAccountPlace");
		Session instance = new Session();
		String expResult = "";
		String result = instance.getAccountPlace();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getLimit method, of class Session.
	 */
	@Test
	public void testGetLimit() throws Exception {
		System.out.println("getLimit");
		Session instance = new Session();
		long expResult = 0;
		long result = instance.getLimit();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
