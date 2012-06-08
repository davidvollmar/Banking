package banking.bank;

/**
 * This connects to the Central Bank web server. 
 * @author David Vollmar <david@vollmar.nl>
 * @since Jun 7, 2012
 */
public class CentralBankConnector {

	/**
	 *
	 * @param rekeningnummer
	 * @return true if valid rekeningnummer
	 */
	public static Boolean checkRekeningNummer(int rekeningnummer) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.checkRekeningNummer(rekeningnummer);
	}

	/**
	 *
	 * @param naam
	 * @param prefix
	 * @return "akkoord" if ok, database error if something is broken.
	 */
	public static String nieuweBank(java.lang.String naam, java.lang.String prefix) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.nieuweBank(naam, prefix);
	}

	/**
	 *
	 * @param nummer
	 * @return
	 */
	public static Boolean bevestigTransactie(int nummer) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.bevestigTransactie(nummer);
	}

	public static String getTransacties(java.lang.String prefix) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.getTransacties(prefix);
	}

	public static java.util.List<banking.bank.Transactie> getTransactiesLijst(java.lang.String prefix) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.getTransactiesLijst(prefix);
	}

	public static String hello(java.lang.String name) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.hello(name);
	}

	public static String nieuwebankrekening(java.lang.String prefix) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.nieuwebankrekening(prefix);
	}

	public static Boolean toevoegentransactie(int vanrekening, int naarrekening, double bedrag) {
		banking.bank.CentraleBankService service = new banking.bank.CentraleBankService();
		banking.bank.BankServer port = service.getBankServerPort();
		return port.toevoegentransactie(vanrekening, naarrekening, bedrag);
	}



}
