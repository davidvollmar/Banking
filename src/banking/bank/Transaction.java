/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
	private Date date;
	private State state;
	


	private enum State{
		RECEIVED_AT_LOCAL_BANK (0),
		RECIEVED_AT_CENTRAL_BANK (1),
		PROCESSED (2),
		ERROR (-1);
		private final int state;
		private State(int state) {
			this.state = state;
		}
		private int getState(){
			return state;
		}
	}

}
