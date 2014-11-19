package kodrasritter.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.SocketException;

import kodrasritter.connection.NetworkController;
import kodrasritter.gui.ChatActionListener;
import kodrasritter.gui.ChatWindow;
import kodrasritter.gui.Displayable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestNetworkcontroller {

	private NetworkController nc;
	private ChatWindow c;
	private static final String MULTICASTIP = "239.34.12.5";
	
	@Before
	public void init() {
		c = new ChatWindow(Mockito.mock(ChatActionListener.class));
		c.setVisible(false);
		this.nc = new NetworkController(c);
	}
	
	@Test
	public void testNetworkInit1() {
		nc.initConnection(MULTICASTIP, 5632, 1);
		assertEquals("239.34.12.5", nc.getGroup().getHostAddress());
		nc.closeConnection();
	}
	
	@Test
	public void testNetworkClose() {
		nc.initConnection(MULTICASTIP, 5632, 1);
		nc.closeConnection();
		assertEquals(null, nc.getSocket().getInetAddress());
	}
	
	@Test
	public void testNetworkSend() throws InterruptedException {
		nc.initConnection(MULTICASTIP, 5632, 1);
		nc.send("Test");
		Thread.sleep(100);
		assertEquals("Test\n", c.getTextArea().getText().substring(c.getTextArea().getText().lastIndexOf(' ')+1));
		nc.closeConnection();
	}
	
	@Test
	public void testNetworkSend2() throws InterruptedException {
		nc.initConnection(MULTICASTIP, 5463, 1);
		nc.send("Hallo");
		nc.send("Test");
		Thread.sleep(100);
		assertEquals("Test\n", c.getTextArea().getText().substring(c.getTextArea().getText().lastIndexOf(' ')+1));
		nc.closeConnection();
	}
	
	@Test
	public void testNetworkSend3() throws InterruptedException {
		nc.initConnection(MULTICASTIP, 5463, 1);
		nc.send("Hallo");
		NetworkController nc2 = new NetworkController(c);
		nc2.initConnection(MULTICASTIP, 5463, 1);
		nc2.send("Test");
		Thread.sleep(200);
		assertEquals("Test\n", c.getTextArea().getText().substring(c.getTextArea().getText().lastIndexOf(' ')+1));
		nc.closeConnection();
	}
	
	@Test
	public void testNetworkSend4() throws InterruptedException {
		nc.initConnection(MULTICASTIP, 5463, 1);
		NetworkController nc2 = new NetworkController(c);
		nc2.initConnection(MULTICASTIP, 5463, 1);
		nc2.closeConnection();
		nc.send("Test");
		Thread.sleep(100);
		assertEquals("Test\n", c.getTextArea().getText().substring(c.getTextArea().getText().lastIndexOf(' ')+1));
		nc.closeConnection();
	}
	
	@Test (expected=NullPointerException.class)
	public void testNetworkSend5() {
		nc.send("Test");
	}
	
	

}
