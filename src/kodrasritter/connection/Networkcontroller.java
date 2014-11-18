package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kodrasritter.gui.Displayable;

/**
 * Diese Klasse steuert die Verbindungen (Verbindungsaufbau und -Abbau).<br>
 * Nachrichten werden für das Senden an den Sender weitergegeben.<br>
 * Empfangene Nachrichten werden an das Display zur Darstellung weitergegeben.<br>
 * 
 * @author Mathias Ritter 4AHIT
 */
public class Networkcontroller implements NetworkControllable {
	
	/** MulticastSocket zum Verbinden zu einer MultiCast-Gruppe */
	private MulticastSocket socket;
	
	/** Sender zum Senden von Nachrichten */
	private Sendable send;
	
	/** Receiver zum Empfangen von Nachrichten */
	private Receiver receive;
	
	/** Display zum Darstellen empfangener Nachrichten */
	private Displayable display;
	
	/** MultiCast-Gruppe */
	private InetAddress group;

	
	public Networkcontroller(Displayable display) {
		this.display = display;
	}
	
	/**
	 * @see NetworkControllable#initConnection(String, int, int)
	 */
	@Override
	public void initConnection(String ip, int port, int ttl) {
		
		try {
			this.socket = new MulticastSocket(port);
			socket.setTimeToLive(ttl);
			group = InetAddress.getByName(ip);
			socket.joinGroup(group);
			
			receive = new Receiver(this, new BufferedReader(new InputStreamReader(new DatagramInputStream(
					socket), "UTF8")));
			
			send = new Sender(new OutputStreamWriter(new DatagramOutputStream(socket, group,
					port), "UTF8"));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Thread receiveThread = new Thread(receive);
		receiveThread.start();
		
	}
	
	/**
	 * @see NetworkControllable#closeConnection()
	 */
	@Override
	public void closeConnection() {
		
		try {
			socket.leaveGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see NetworkControllable#receive(String)
	 */
	@Override
	public void receive(String content) {
		if (display != null)
			display.updateChatDisplay(content);
	}
	
	/**
	 * @see NetworkControllable#send(String)
	 */
	@Override
	public void send(String content) {
		send.send(content);
	}

}
