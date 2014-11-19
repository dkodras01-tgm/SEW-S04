package kodrasritter;

import java.io.IOException;
import java.net.SocketException;

public class Main {

	public static void main(String[] args) {

		String usage = "Bitte verwenden Sie folgende Parameter beim Aufruf: <multicast-ip> <port> [ttl]";
		
		if (!checkArgs(args)) {
			System.out.println(usage);
		} else {
			try {
				if (args.length == 0)
					new Controller("239.45.23.4", 36327, 1);
				if (args.length == 2)
					new Controller(args[0], Integer.parseInt(args[1]), 1);
				if (args.length == 3)
					new Controller(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])); 
				
			} catch (SocketException e) {
				System.out.println("Bitte starten Sie das Jar-File mit folgendem JVM-Argument: -Djava.net.preferIPv4Stack=true");
				System.out.println("Beispiel: java -Djava.net.preferIPv4Stack=true -jar S04.jar");
				System.exit(-1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	

	}

	public static boolean checkArgs(String[] args) {

		if ((args.length != 0) && (args.length != 2) && (args.length != 3)) {
			return false;
		}

		if ((args.length == 2) || (args.length == 3)) {
			
			//Teilen der IP in die einzelnen Teile
			String ipparts[] = args[0].split("\\.");

			// Ueberpruefen, ob IP 4 Teile hat
			if (ipparts.length != 4) {
				System.out
						.println("Bitte geben Sie eine korrekte Multicast-IP mit exakt 4 durch einen Punkt getrennten Zahlen an!");
				return false;
			}

			// Ueberpruefen der IP
			for (int i = 0; i < ipparts.length; i++) {
				try {
					// Ueberpruefen, ob IP numerisch ist
					int act = Integer.parseInt(ipparts[i]);

					if (i == 0) {
						// Ueberprufen, ob erste Stelle korrekt ist
						if ((act < 224) || (act > 239)) {
							System.out
									.println("Bitte geben Sie eine korrekte Multicast-IP an!");
							System.out
									.println("Erste Stelle: >= 224 und <= 239, alle anderen Stellen: >= 0 und <= 255");
							return false;
						}
					} else {
						// Ueberpruefen, ob alle anderen Stellen korrekt sind
						if ((act < 0) || (act > 255)) {
							System.out
									.println("Bitte geben Sie eine korrekte Multicast-IP an!");
							System.out
									.println("Erste Stelle: >= 224 und <= 239, alle anderen Stellen: >= 0 und <= 255");
							return false;
						}
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

		}

		if (args.length == 3) {

			// Uebepruefen der TTL
			try {
				int ttl = Integer.parseInt(args[2]);

				if ((ttl < 1) || (ttl > 255)) {
					System.out
							.println("Die Time To Live (TTL) muss eine Zahl zwischen 1 und 255 sein!");
					return false;
				}

			} catch (NumberFormatException e) {
				System.out
						.println("Die Time To Live (TTL) muss eine Zahl zwischen 1 und 255 sein!");
				return false;
			}

		}

		return true;
	}
}
