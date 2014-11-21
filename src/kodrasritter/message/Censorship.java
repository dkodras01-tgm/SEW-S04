package kodrasritter.message;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Diese Klasse ersetzt boese Woerter in der Nachricht.
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class Censorship extends Modifier {

	private String[] badwords = { "shit", "fuck", "scheisse", "arsch",
			"arschloch" };

	/**
	 * Im Konstruktor wird der super-Konstrukter aufgerufen
	 * 
	 * @param m Message
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	public Censorship(Message m) throws IOException {
		super(m);
	}

	/**
	 * Zensur wird ausgefuehrt
	 * 
	 * @return zensierte Nachricht
	 */
	public String process() {
		return censor(getMessage().process());
	}

	/**
	 * Woerter in der Nachricht werden Zensiert
	 * 
	 * @param content Inhalt einer Nachricht, die zensiert werden soll
	 * @return zensierte Nachricht
	 */
	public String censor(String content) {

		List<String> temp = Arrays.asList(content.split(" "));

		for (int i = 0; i < temp.size(); i++) {
			for (int k = 0; k < badwords.length; k++) {
				if (temp.get(i).equalsIgnoreCase(badwords[k])) {
					temp.set(i, "$%&*");
				}
			}

		}

		StringBuilder sb = new StringBuilder();

		String trenner = "";
		for (String act : temp) {
			sb.append(trenner + act);
			trenner = " ";
		}

		return sb.toString();
	}

}
