package banking.bank;

import java.util.Date;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Jun 8, 2012
 */
public class Transaction {

	private int accountTo;
	private int accountFrom;
	private int amount;
	private Date date;
	private State state;

	public Transaction(int accountTo, int accountFrom, Date date, int amount) {
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.date = date;
		this.state = State.RECEIVED_AT_LOCAL_BANK;
		this.amount = amount;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public Date getDate() {
		return (Date) date.clone();
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	public int getAmount(){
		return amount;
	}

	public enum State {

		RECEIVED_AT_LOCAL_BANK(0),
		RECIEVED_AT_CENTRAL_BANK(1),
		PROCESSED(2),
		ERROR(-1);
		private final int state;

		private State(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

	public String[] toStringArray(){
		return toStringArray(this);
	}

	public static String[] toStringArray(Transaction transaction) {
		if(transaction == null){
			throw new NullPointerException();
		}
		String[] ret = {
			transaction.getAccountFrom() + "",
			transaction.getAccountTo() + "",
			transaction.getDate().toString(),
			transaction.getAmount()+"",
			transaction.getState().toString()
		};
		return ret;

	}
}
