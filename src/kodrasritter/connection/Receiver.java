package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Diese Klasse liest Strings von einem Stream ein und leitet diese an den Networkcontroller weiter.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class Receiver implements Runnable {
	
	private NetworkControllable control;
	private BufferedReader in;

	public Receiver(NetworkControllable control, BufferedReader in) {
		this.control = control;
		this.in = in;
	}
	
	
	@Override
	public void run() {
		String input;
		try {
			//Es wird so lange vom Stream gelesen, so lange dieser nicht null ist
			while ((input = in.readLine()) != null) {
				//Zum Empfangen wird receive aufgerufen
				control.receive(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
