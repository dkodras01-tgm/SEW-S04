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
		ClientReceiver clientReceiver = (new ClientReceiver(c, this));
		new Thread(clientReceiver).start();
		clientThreads.put(c, clientReceiver);
		clients.add(c);
	}
	
	/**
	 * @throws IOException 
	 * @see Server#removeClient(Client)
	 */
	public void removeClient(Client c) throws IOException {	
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
	 * @throws IOException 
	 * @see Server#beenden
	 */
	public void beenden() throws IOException {
		this.updateClients("Der Server wurde beendet!");
		beenden = true;
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
