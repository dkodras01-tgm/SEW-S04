package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import kodrasritter.connection.NetworkControllable;

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
	
	public ChatClient(Socket s, NetworkControllable nc) throws IOException {
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintWriter(s.getOutputStream(), true);
		this.control = nc;
		
	}
	
	public BufferedReader getInput() {
		return this.in;
	}
	
	public PrintWriter getOutput() {
		return this.out;
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
