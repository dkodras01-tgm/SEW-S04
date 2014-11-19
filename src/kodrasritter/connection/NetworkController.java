package kodrasritter.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import kodrasritter.gui.Displayable;

public class NetworkController implements NetworkControllable {

	private Client client;
	private Server server;
	private Displayable display;
	
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
			this.server.updateClients("Der Server wurde beendet!");
			this.server.beenden();
		}
	}

	@Override
	public void initConnection(String ip, int port) throws IOException {
		
		
		//Herausfinden, ob bereits ein Server auf der angegebenen IP und dem angegebenen Port läuft
		try {
			new Socket(ip, port);
			
//			final Socket sock = new Socket();
//			final int timeOut = (int)TimeUnit.SECONDS.toMillis(1); // 5 sec wait period
//			sock.connect(new InetSocketAddress("host", 8080), timeOut);
			
		} catch (ConnectException e) {			
			
			this.server = new ChatServer(this, port);
			new Thread(this.server).start();
			
		}
		
		this.client = new ChatClient(new Socket(ip, port), this);	
		
		new Thread(this.client).start();

		
		
	}
	

}
