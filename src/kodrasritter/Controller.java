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

public class Controller {
	
	private NetworkControllable net;
	private Displayable display;
	
	private ActionListener al;
	
	
	public Controller(String ip, int port, int ttl) throws IOException {
		
		al = new ChatActionListener(this);
		display = new ChatWindow(al, "Multicast-Chat [" + ip + ":" + port + "]");
		net = new NetworkController(display);
		
		net.initConnection(ip, port, ttl);
		
	}
	
	
	public void send() {
		
		Message m = new ChatMessage();
		m.setContent(display.getUserInput());
		
		List<String> options = display.getOptions();
		
		if (options.contains("ToUpperCase"))
			m = new UpperCase(m);
		if (options.contains("DoubleLetter"))
			m = new DoubleCharacter(m);
		if (options.contains("Censor"))
			m = new Censorship(m);
		
		m.setContent(m.process());
		
		try {
			net.send(m.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		display.updateUserInput("");
	}
	
	public void closeConnection() {
		try {
			net.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public ActionListener getAl() {
		return this.al;
	}


	public Displayable getDisplay() {
		return this.display;
	}

}
