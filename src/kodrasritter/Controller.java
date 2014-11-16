package kodrasritter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kodrasritter.connection.NetworkControllable;
import kodrasritter.connection.Networkcontroller;
import kodrasritter.gui.ChatActionListener;
import kodrasritter.gui.ChatWindow;
import kodrasritter.gui.Display;
import kodrasritter.gui.Displayable;
import kodrasritter.message.ChatMessage;
import kodrasritter.message.Message;

public class Controller {
	
	private NetworkControllable net;
	private Displayable dp;
	private ChatWindow cw;
	
	public Controller() {
		ActionListener al = new ChatActionListener(this);
		cw = new ChatWindow(al);
		dp = new Display(cw);
		net = new Networkcontroller(dp);
		initConnection("239.46.194.20", 1234, 10);
	}
	
	public boolean messageSent(ActionEvent e) {
		return (e.getSource() == cw.getBtnSenden() || e.getSource() == cw.getTextField());
	}
	
	public void send() {
		
		Message m = new ChatMessage();
		m.setContent(dp.getUserInput());
		
		// TODO Message je nach Checkboxen dekorieren
		
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
		new Controller();
	}

}
