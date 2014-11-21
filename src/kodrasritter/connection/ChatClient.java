package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Ein Client empfaengt Nachrichten eines angegebenen Servers.
 * Empfanegt er eine Nachricht, so meldet er sich beim NetworkController.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class ChatClient implements Client, Runnable {
	
	private BufferedReader in;
	private PrintWriter out;
	private NetworkControllable control;
	private Socket client;
	
	/**
	 * Abfragen der Streams von einem angegebenen Socket, Initialisierung aller Attribute
	 * 
	 * @param s Socket
	 * @param nc Networkcontroller
	 * @throws IOException Exception beim Abfragen des Streams vom Socket
	 */
	public ChatClient(Socket s, NetworkControllable nc) throws IOException {
		this.client = s;
		this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		this.out = new PrintWriter(client.getOutputStream(), true);
		this.control = nc;
		
	}
	
	/**
	 * @see Client#getSocket()
	 */
	public Socket getSocket() {
		return this.client;
	}
	
	/**
	 * @see Client#getInput()
	 */
	public BufferedReader getInput() {
		return this.in;
	}
	
	/**
	 * @see Client#getOutput()
	 */
	public PrintWriter getOutput() {
		return this.out;
	}
	
	/**
	 * @see Client#send(String)
	 */
	public void send(String content) throws IOException {
		out.println(content);
	}

	@Override
	public void run() {
		String input;
		try {
			while ((input = in.readLine()) != null) {
				//Zum Empfangen wird receive aufgerufen
				control.receive(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
