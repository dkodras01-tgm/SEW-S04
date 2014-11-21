package kodrasritter.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import kodrasritter.gui.Displayable;

public class NetworkController implements NetworkControllable {

	private Client client;
	private Server server;
	private Displayable display;
	private String serverIP;
	
	public NetworkController(Displayable display) {
		this.display = display;
	}
	
	@Override
	public void receive(String content) {
		display.updateChatDisplay(content);
		
	}

	@Override
	public void send(String content) throws IOException {
		client.send(content);	
	}

	@Override
	public void closeConnection() throws IOException {
		if (server != null) {
			this.server.beenden();
		}
	}

	@Override
	public void initConnection(String ip, int port) throws IOException {
		
		
		//Herausfinden, ob bereits ein Server auf der angegebenen IP und dem angegebenen Port läuft
		try {
			new Socket(ip, port);
			
			//Mit vorhandenem Server verbinden
			this.client = new ChatClient(new Socket(ip, port), this);
			this.serverIP = ip;
			
		} catch (ConnectException e) {			
			
			//Neuen Server erstellen und mit diesem verbinden
			this.server = new ChatServer(this, port);
			new Thread(this.server).start();
			this.client = new ChatClient(new Socket("127.0.0.1", port), this);
			this.serverIP = InetAddress.getLocalHost().getHostAddress();
				
		}
		
		//Titel des Chats wird auf die IP des Servers geandert
		this.display.setDisplayTitle("Chat [" + serverIP + ":" + port + "]");	
		
		new Thread(this.client).start();	
		
	}
	

}
