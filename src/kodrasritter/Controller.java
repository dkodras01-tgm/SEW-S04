package kodrasritter;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import kodrasritter.connection.NetworkControllable;
import kodrasritter.connection.NetworkController;
import kodrasritter.gui.ChatActionListener;
import kodrasritter.gui.ChatWindow;
import kodrasritter.gui.Displayable;
import kodrasritter.message.Censorship;
import kodrasritter.message.ChatMessage;
import kodrasritter.message.DoubleCharacter;
import kodrasritter.message.Message;
import kodrasritter.message.UpperCase;

/**
 * Dient zur Kommunikation zwischen GUI und Netzwerkcontroller.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class Controller {
	
	private NetworkControllable net;
	private Displayable display;
	
	private ActionListener al;
	
	/**
	 * Initialisierung Anzeige (GUI) und Netzwerkkommunikation
	 * 
	 * @param ip Die IP des Servers (Falls kein Server vorhanden, wird ein neuer Server lokal erstellt)
	 * @param port Port, ueber den die Kommunikation laufen soll
	 * @throws IOException Fehler beim Initialisieren der Connection
	 */
	public Controller(String ip, int port) throws IOException {
		
		al = new ChatActionListener(this);
		display = new ChatWindow(al);
		net = new NetworkController(display);
		
		net.initConnection(ip, port);
		
	}
	
	
	/**
	 * Senden einer Nachricht <br>
	 * Dabei wird der Userinput von der Anzeige genommen und eine ChatMessage erstellt.<br>
	 * Diese wird je nach ausgewaehlten Optionen dekoriert (Decorator Pattern).<br>
	 * Danach wird der Inhalt der Nachricht mittels des Networkcontrollers gesendet.
	 */
	public void send() {
		
		try {

			//Neue ChatMessage mit Inhalt der Benutzereingabe
			Message m = new ChatMessage();
			m.setContent(display.getUserInput());

			//Angehakte Optionen abfragen
			List<String> options = display.getOptions();

			if (options.contains("Censor"))
				m = new Censorship(m);
			if (options.contains("ToUpperCase"))
				m = new UpperCase(m);
			if (options.contains("DoubleLetter"))
				m = new DoubleCharacter(m);
			
			//Chatnachricht verarbeiten
			m.setContent(m.process());

			//Inhalt senden
			net.send(m.getContent());

			//Benutzereingabe zuruecksetzen
			display.updateUserInput("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}
	
	/**
	 * Beenden der Connection (Aufruf einer entsprechenden Methode im Networkcontroller).
	 */
	public void closeConnection() {
		try {
			net.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rueckgabe des aktuellen Actionlisteners fuer die Anzeige.
	 * 
	 * @return Den ActionListener
	 */
	public ActionListener getAl() {
		return this.al;
	}

	/**
	 * Rueckgabe der aktuell verwendeten Anzeige.
	 * 
	 * @return Die Anzeige 
	 */
	public Displayable getDisplay() {
		return this.display;
	}
	

}
