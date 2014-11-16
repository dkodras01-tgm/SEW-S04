package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kodrasritter.gui.Displayable;

public class Networkcontroller implements NetworkControllable {
	
	private MulticastSocket socket;
	private Sendable send;
	private Receiver receive;
	private Displayable display;
	private InetAddress group;

	
	public Networkcontroller(Displayable display) {
		this.display = display;
	}
	
	
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
	
	@Override
	public void closeConnection() {
		
		try {
			socket.leaveGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void receive(String content) {
		if (display != null)
			display.updateInputDisplay(content);
	}
	
	@Override
	public void send(String content) {
		send.send(content);
	}

}
