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
	
	
	public Controller(String ip, int port, int ttl) {
		
		ActionListener al = new ChatActionListener(this);
		display = new ChatWindow(al, "Multicast-Chat [" + ip + ":" + port + "]");
		net = new NetworkController(display);
		
		try {
			net.initConnection(ip, port, ttl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
	
	public static void main(String[] args) {
		
//		String file = ClassLoader.getSystemClassLoader().getResource(".").getPath() + "S04.jar";
//		
//		if (args.length == 0) {
//			try {
//				Runtime.getRuntime().exec(new String[] {"java", "-Djava.net.preferIPv4Stack=true", "-jar", file, "argset"});
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  
//		} else {
//			new Controller();
//		}
		
		new Controller("239.46.194.21", 1234, 1);
		
	}

}
