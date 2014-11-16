package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MulticastSocket;

import kodrasritter.gui.Displayable;

public class Networkcontroller implements NetworkControllable {
	
	MulticastSocket socket;
	Sendable send;
	Receiver receive;
	Displayable display;
	
	static final String DEFAULT_IP = "239.46.194.20";
	static final int DEFAULT_PORT = 1234;
	static final int DEFAULT_TTL = 10;
	
	public Networkcontroller() {
		try {
			initNet(DEFAULT_IP, DEFAULT_PORT, DEFAULT_TTL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Networkcontroller(Displayable display) {
		try {
			initNet(DEFAULT_IP, DEFAULT_PORT, DEFAULT_TTL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.display = display;
	}
	
	public Networkcontroller(String ip, int port, int ttl, Displayable display) {
		try {
			initNet(ip, port, ttl);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		this.display = display;
	}
	
	
	public void initNet(String ip, int port, int ttl) throws IOException {
		
		this.socket = new MulticastSocket(port);
		socket.setTimeToLive(ttl);
		InetAddress group = InetAddress.getByName(ip);
		socket.joinGroup(group);
		
		receive = new Receiver(this, new BufferedReader(new InputStreamReader(new DatagramInputStream(
				socket), "UTF8")));
		
		send = new Sender(new OutputStreamWriter(new DatagramOutputStream(socket, group,
				port), "UTF8"));
		
		Thread receiveThread = new Thread(receive);
		receiveThread.start();
		
		
	}

	@Override
	public void receive(String content) {
		if (display != null)
			display.updateOutput(content);
	}
	
	public void send(String content) {
		try {
			send.send(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
