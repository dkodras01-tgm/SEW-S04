package kodrasritter;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		String usage = "Bitte verwenden Sie folgende Parameter beim Aufruf: <server-ip> <port>";

		if (!checkArgs(args)) {
			System.out.println(usage);
		} else {
			try {
				new Controller(args[0], Integer.parseInt(args[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static boolean checkArgs(String[] args) {

		if (args.length != 2) {
			return false;
		}

		// Teilen der IP in die einzelnen Teile
		String ipparts[] = args[0].split("\\.");

		// Ueberpruefen, ob IP 4 Teile hat
		if (ipparts.length != 4) {
			System.out
					.println("Bitte geben Sie eine korrekte IP mit exakt 4 durch einen Punkt getrennten Zahlen an!");
			return false;
		}

		// Ueberpruefen der IP
		for (int i = 0; i < ipparts.length; i++) {
			try {
				// Ueberpruefen, ob IP numerisch ist
				int act = Integer.parseInt(ipparts[i]);

				// Ueberpruefen, ob alle anderen Stellen korrekt sind
				if ((act < 0) || (act > 255)) {
					System.out
							.println("Bitte geben Sie eine korrekte IP an (4 Teile zwischen: >= 0 und <= 255)!");
					return false;
				}

			} catch (NumberFormatException e) {
				System.out
						.println("Die angegebene IP-Adresse darf nur Zahlen und Punkte zur Trennung enthalten!");
				return false;
			}
		}

		// Uebepruefen des Ports
		try {
			int port = Integer.parseInt(args[1]);

			if ((port < 0) || (port > 65535)) {
				System.out
						.println("Der Port muss eine Zahl zwischen 0 und 65535 sein!");
				return false;
			}

		} catch (NumberFormatException e) {
			System.out
					.println("Der Port muss eine Zahl zwischen 0 und 65535 sein!");
			return false;
		}

		return true;
	}
}
