package kodrasritter;

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
	
	public Controller() {
		ActionListener al = new ChatActionListener(this);
		ChatWindow cw = new ChatWindow(al);
		dp = new Display(cw);
		net = new Networkcontroller(dp);
	}
	
	
	public void send() {
		
		Message m = new ChatMessage();
		m.setContent(dp.getInput());
		
		// TODO Message je nach Checkboxen dekorieren
		
		net.send(m.getContent());
		dp.updateInput("");
	}
	
	public static void main(String[] args) {
		new Controller();
	}

}
