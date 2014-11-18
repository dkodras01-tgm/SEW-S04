package kodrasritter;

import java.awt.event.ActionListener;
import java.util.List;

import kodrasritter.connection.NetworkControllable;
import kodrasritter.connection.Networkcontroller;
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
	
	
	public Controller() {
		
		ActionListener al = new ChatActionListener(this);
		
		display = new ChatWindow(al);
		net = new Networkcontroller(display);
		initConnection("239.46.194.21", 1234, 10);
		
//		net = new Networkcontroller2(dp);
//		initConnection("127.0.0.1", 1244, 10);
		
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
		
		net.send(m.getContent());
		display.updateUserInput("");
	}
	
	public void closeConnection() {
		net.closeConnection();
	}
	
	public void initConnection(String ip, int port, int ttl) {
		net.initConnection(ip, port, ttl);
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
		
		new Controller();
		
	}

}
