package kodrasritter.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Der ChatServer empfanegt Nachrichten der Clients und sendet diese an alle weiter.
 * Verbindet sich ein neuer Client, so wird dieser sofort registriert und erhaelt ebenfalls alle weiteren Nachrichten.<br>
 * Es koennen mehr als 2 Clients somit miteinander kommunizieren.
 * 
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public class ChatServer implements Server {
	
	private ServerSocket server;
	private HashMap<Client, ClientReceiver> clientThreads;
	private List<Client> clients;
	private NetworkControllable control;
	private boolean beenden;
	
	/**
	 * Festlegen des Ports und Initialisieren aller anderen Attribute
	 * 
	 * @param nc Networkcontroller
	 * @param port Port, ueber den die Kommunikation laufen soll
	 * @throws IOException Fehler waehrend der Kommunikation
	 */
	public ChatServer(NetworkControllable nc, int port) throws IOException {
		server = new ServerSocket(port);
		clientThreads = new HashMap<Client, ClientReceiver>();
		clients = new ArrayList<Client>();
		beenden = false;
		
	}
	
	/**
	 * @see Server#addClient(Client)
	 */
	public void addClient(Client c) {
		//Neuen Client hinzufuegen und daher auch neuen ClientReceiver-Thread starten
		ClientReceiver clientReceiver = (new ClientReceiver(c, this));
		new Thread(clientReceiver).start();
		clientThreads.put(c, clientReceiver);
		clients.add(c);
	}
	
	/**
	 * @see Server#removeClient(Client)
	 */
	public void removeClient(Client c) throws IOException {	
		//ClientReceiver Thread beenden und von Listen entfernen
		clientThreads.get(c).beenden();
		clientThreads.remove(c);
		clients.remove(c);
	}
	
	/**
	 * @see Server#updateClients(String)
	 */
	public void updateClients(String s) throws IOException {
		for (Client c : clients) {
			c.getOutput().println(s);
		}
		
	}

	/**
	 * @see Server#beenden
	 */
	public void beenden() throws IOException {
		//Clients benachrichtigen
		this.updateClients("Der Server wurde beendet!");
		beenden = true;
		
		//Alle Clients abmelden
		int size = clients.size();
		for (int i=size-1; i>=0; i--) {
			removeClient(clients.get(i));
			size = clients.size();
		}
			
			
	}

	@Override
	public void run() {
		while(!beenden) {
			try {
				//Neue Clients werden hinzugefuegt, sobald sie sich zum Socket connecten
				Client c = new ChatClient(this.server.accept(), control);
				this.addClient(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
