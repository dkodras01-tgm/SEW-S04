package kodrasritter.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import kodrasritter.connection.DatagramOutputStream;
import kodrasritter.connection.Sender;

import org.junit.Before;
import org.junit.Test;

public class TestSender {

	private Sender s;
	private MulticastSocket socket;
	
	@Before
	public void init() throws UnsupportedEncodingException, UnknownHostException, IOException {
		socket = new MulticastSocket(3422);
		socket.setTimeToLive(1);
		socket.joinGroup(InetAddress.getByName("239.34.23.6"));
		this.s = new Sender(new OutputStreamWriter(new DatagramOutputStream(socket, InetAddress.getByName("239.34.23.6"), 3442), "UTF8"));
		
//		this.s = new Sender(new OutputStreamWriter(System.out));
	}
	
	@Test
	public void test() {
		s.send("Hallo");
		socket.get
	}

}
