package kodrasritter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main-Methode, Ueberpruefung der Kommandozeilenargumente, Initialisierung Chat
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class Main {

	private static Logger logger = LogManager.getLogger(Main.class.getName());;

	public static void main(String[] args) {

		String usage = "Bitte verwenden Sie folgende Parameter beim Aufruf: <server-ip> <port>";

		//Kommandozeilenargumente ueberprufen
		if (!checkArgs(args)) {
			logger.error(usage);
		} else {
			try {
				logger.info("Chat starting...");
				//Neuen Chat initialisieren (Mit angegebener IP und Port)
				new Controller(args[0], Integer.parseInt(args[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Ueberpruefung der Kommandozeilenargumente
	 * 
	 * @param args Kommandozeileargumente
	 * @return Ob Argumente korrekt
	 */
	public static boolean checkArgs(String[] args) {

		if (args.length != 2) {
			return false;
		}

		// Teilen der IP in die einzelnen Teile
		String ipparts[] = args[0].split("\\.");

		// Ueberpruefen, ob IP 4 Teile hat
		if (ipparts.length != 4) {
			logger.error("Bitte geben Sie eine korrekte IP mit exakt 4 durch einen Punkt getrennten Zahlen an!");
			return false;
		}

		// Ueberpruefen der IP
		for (int i = 0; i < ipparts.length; i++) {
			try {
				// Ueberpruefen, ob IP numerisch ist
				int act = Integer.parseInt(ipparts[i]);

				// Ueberpruefen, ob alle anderen Stellen korrekt sind
				if ((act < 0) || (act > 255)) {
					logger.error("Bitte geben Sie eine korrekte IP an (4 Teile zwischen: >= 0 und <= 255)!");
					return false;
				}

			} catch (NumberFormatException e) {
				logger.error("Die angegebene IP-Adresse darf nur Zahlen und Punkte zur Trennung enthalten!");
				return false;
			}
		}

		// Uebepruefen des Ports
		try {
			int port = Integer.parseInt(args[1]);

			if ((port < 0) || (port > 65535)) {
				logger.error("Der Port muss eine Zahl zwischen 0 und 65535 sein!");
				return false;
			}

		} catch (NumberFormatException e) {
			logger.error("Der Port muss eine Zahl zwischen 0 und 65535 sein!");
			return false;
		}

		return true;
	}
}
