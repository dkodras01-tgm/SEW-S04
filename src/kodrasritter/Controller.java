package kodrasritter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kodrasritter.connection.NetworkControllable;
import kodrasritter.connection.Networkcontroller;
import kodrasritter.gui.ChatActionListener;
import kodrasritter.gui.ChatWindow;
import kodrasritter.gui.Display;
import kodrasritter.gui.Displayable;
import kodrasritter.message.Censorship;
import kodrasritter.message.ChatMessage;
import kodrasritter.message.DoubleCharacter;
import kodrasritter.message.Message;
import kodrasritter.message.UpperCase;

public class Controller {
	
	private NetworkControllable net;
	private Displayable dp;
	private ChatWindow cw;
	
	
	public Controller() {
		ActionListener al = new ChatActionListener(this);
		cw = new ChatWindow(al);
		dp = new Display(cw);
		net = new Networkcontroller(dp);
		initConnection("239.46.194.21", 1234, 10);
//		net = new Networkcontroller2(dp);
//		initConnection("127.0.0.1", 1244, 10);
		
	}
	
	public boolean messageSent(ActionEvent e) {
		return (e.getSource() == cw.getBtnSenden() || e.getSource() == cw.getTextField());
	}
	
	public void send() {
		
		Message m = new ChatMessage();
		m.setContent(dp.getUserInput());
		if(cw.isToUpperCase())
			m = new UpperCase(m);
		if(cw.isDoubleLetter())
			m = new DoubleCharacter(m);
		if(cw.isCensor())
			m = new Censorship(m);
		System.out.println(m.getContent());
		net.send(m.getContent());
		dp.updateUserInput("");
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
