package kodrasritter.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kodrasritter.gui.Displayable;


/**
 * Diese Klasse steuert die Verbindungen (Verbindungsaufbau und -Abbau).<br>
 * Nachrichten koennen gesendet und empfangen werden.<br>
 * Empfangene Nachrichten werden an das Display zur Darstellung weitergegeben.<br>
 * 
 * @author Mathias Ritter 4AHIT
 */
public class NetworkController implements NetworkControllable {
	
	/** MulticastSocket zum Senden von Nachrichten */
	private MulticastSocket clientSocket;
	
	/** DatagramSocket zum Empfangen von Nachrichten */
	private DatagramSocket serverSocket;
	
	/** Display zum Darstellen empfangener Nachrichten */
	private Displayable display;
	
	/** MultiCast-Gruppe */
	private InetAddress group;
	
	/** Time-To-Live: max. Anzahl an Routern von A nach B */
	private int ttl;
	
	/** Port, ueber den die Kommunikation laeuft */
	private int port;
	
	/** Multicast-IP-Adresse */
	private String ip;

	/**
	 * Initialisieren der Chat-Anzeige.<br>
	 * Zum Initialisieren einer neuen Verbindung muss {@link #initConnection(String, int, int)}
	 * aufgerufen werden.
	 * 
	 * @param display Chat-Anzeige
	 */
	public NetworkController(Displayable display) {
		this.display = display;
	}
	
	/**
	 * @see NetworkControllable#initConnection(String, int, int)
	 */
	@Override
	public void initConnection(String ip, int port, int ttl) throws IOException {
			
		this.ttl = ttl;
		this.ip = ip;
		this.port = port;
        this.group = InetAddress.getByName(ip);
        
        //Initialisieren des MultiCast Sockets zum Senden
        this.clientSocket = new MulticastSocket(port);
        this.clientSocket.setTimeToLive(this.ttl);
    	this.clientSocket.joinGroup(group);	
    	
    	//Initialisieren des DatagramSockets zum Empfangen
    	this.serverSocket = new DatagramSocket();
    	
    	//Aufruf der Receive Methode
    	//Ab jetzt werden alle eingehenden Nachrichten empfangen
    	this.receive();
		
	}
	
	/**
	 * @throws IOException 
	 * @see NetworkControllable#closeConnection()
	 */
	@Override
	public void closeConnection() throws IOException {
		clientSocket.leaveGroup(group);
	}

	/**
	 * @throws IOException 
	 * @see NetworkControllable#receive(String)
	 */
	@Override
	public void receive() throws IOException {
			
		while (true) {
			
			//In diesem Buffer werden die ankommenden Nachrichten zwischengespeichert.
			//1024 Byte sind ausreichend, da die Nachrichten nicht besonders gross sind.
			byte[] buf = new byte[1024];

			// Ein neues DatagramPacket wird zum Empfangen der Nachricht initialisiert
			DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
			
			//Nachricht mit DatagramPacket empfangen
			clientSocket.receive(msgPacket);
			
			//Die Nachricht wird in einen String umgewandelt
			String msg = new String(buf, 0, buf.length);
			
			buf = null;
			
			//Updaten der Anzeige
			display.updateChatDisplay(msg);
		}
					
	}
	
	/**
	 * @see NetworkControllable#send(String)
	 */
	@Override
	public void send(String content) throws IOException {

			//Ein neues DatagramPacket wird erstellt, um eine Nachricht zu senden.
			//Es enthaelt die Nachricht in Form von Bytes
			DatagramPacket msgPacket = new DatagramPacket(content.getBytes(),
					content.getBytes().length, InetAddress.getByName(ip), port);
			
			//Senden des DatagramPackets
			serverSocket.send(msgPacket);
	}

	public MulticastSocket getSocket() {
		return clientSocket;
	}

	public InetAddress getGroup() {
		return group;
	}

}
