package kodrasritter.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import kodrasritter.connection.NetworkControllable;
import kodrasritter.gui.Displayable;

public class NetworkController implements NetworkControllable {

	private Client c;
	private Server s;
	private Displayable display;
	
	public NetworkController(Displayable display) {
		this.display = display;
	}
	
	@Override
	public void receive(String content) {
		display.updateChatDisplay(content);
		
	}

	@Override
	public void send(String content) {
		try {
			c.send(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void closeConnection() throws IOException {
		if (s != null)
			this.s.updateClients("Der Server wurde beendet!");
	}

	@Override
	public void initConnection(String ip, int port) throws IOException {
		
		
		//Herausfinden, ob bereits ein Server auf der angegebenen IP und dem angegebenen Port läuft
		try {
			new Socket(ip, port);
			
		} catch (ConnectException e) {			
			
				this.s = new ChatServer(this, port);
				new Thread(this.s).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
			
		this.c = new ChatClient(new Socket(ip, port), this);	
			
		new Thread(this.c).start();

		
		
	}
	

}
