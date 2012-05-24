/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package banking.centralbank;

import banking.bank.Bank;
import java.util.ArrayList;

/**
 *
 * @author David Vollmar <david@vollmar.nl>
 * @since Apr 6, 2012
 */
public class CentralBank {
	private ArrayList<Bank> banks;

	public CentralBank() {
		banks = new ArrayList<>();
	}

	public boolean addBank(Bank b){
		if(b == null){
			return false;
		}
		return banks.add(b);
	}



}
