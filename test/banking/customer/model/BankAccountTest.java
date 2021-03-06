package banking.customer.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 */
public class BankAccountTest {

	/**
	 * Test of add method, of class BankAccount.
	 */
	@Test
	public void testAdd() {
		System.out.println("add");
		BankAccount instance = new BankAccount(1, "name", "place", "password");
		instance.add(300);
		assertTrue(instance.getSaldo() == 300);
		instance.add(0);
		assertTrue(instance.getSaldo() == 300);
		instance.add(-1);
		assertTrue(instance.getSaldo() == 300);
	}

	/**
	 * Test of subtract method, of class BankAccount.
	 */
	@Test
	public void testSubtract() {
		System.out.println("subtract");

		BankAccount instance = new BankAccount(1, "name", "place", "password");
		instance.setLimit(0);
		instance.add(300);
		assertTrue(instance.subtract(300));
		assertTrue(instance.getSaldo() == 0);
		assertFalse(instance.subtract(1));
		instance.add(300);
		assertTrue(instance.subtract(-300));
		assertTrue(instance.getSaldo() == 0);

	}

	/**
	 * Test of canSubtract method, of class BankAccount.
	 */
	@Test
	public void testCanSubtract() {
		System.out.println("canSubtract");
		BankAccount instance = new BankAccount(1, "name", "place", "password");
		instance.setLimit(0);
		instance.add(300);
		assertTrue(instance.canSubtract(300));
		instance.setLimit(-1000);
		assertTrue(instance.canSubtract(1300));
		assertFalse(instance.canSubtract(1301));
	}
}
