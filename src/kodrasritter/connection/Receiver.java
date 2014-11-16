package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver implements Runnable {
	
	private NetworkControllable cm;
	private BufferedReader in;

	public Receiver(NetworkControllable cm, BufferedReader in) {
		this.cm = cm;
		this.in = in;
	}
	
	
	@Override
	public void run() {
		String input;
		try {
			while ((input = in.readLine()) != null) {
				cm.receive(input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
