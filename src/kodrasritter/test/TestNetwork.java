package kodrasritter.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import kodrasritter.connection.NetworkController;
import kodrasritter.gui.ChatActionListener;
import kodrasritter.gui.ChatWindow;
import kodrasritter.gui.Displayable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestNetwork {

	private NetworkController nc;
	private ChatWindow c;
	
	/**
	 * Initialisieren der Attribute fuer die Testcases
	 */
	@Before
	public void init() {
		c = new ChatWindow(Mockito.mock(ChatActionListener.class));
		c.setVisible(false);
		this.nc = new NetworkController(c);
	}
	
	/**
	 * Testen, ob eine Connection aufgebaut werden kann
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNetworkInit1() throws IOException {
		nc.initConnection("127.0.0.1", 5627);
//		assertEquals("239.34.12.5", nc.getGroup().getHostAddress());
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob eine Connection auch wieder erfolgreich beendet wird
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNetworkClose() throws IOException {
		nc.initConnection("127.0.0.1", 5639);
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob eine gesendete Nachricht korrekt ankommt
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testNetworkSend1() throws IOException, InterruptedException {
		nc.initConnection("127.0.0.1", 5631);
		nc.send("Test");
		Thread.sleep(100);
		assertEquals(
				"Test",
				c.getTextArea()
						.getText()
						.substring(
								c.getTextArea().getText().lastIndexOf(']') + 2,
								c.getTextArea().getText().lastIndexOf('t') + 1));
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob eine gesendete Nachricht nach einer bereits anderen gesendeten ankommt
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testNetworkSend2() throws IOException, InterruptedException {
		nc.initConnection("127.0.0.1", 5463);
		nc.send("Hallo");
		nc.send("Test");
		Thread.sleep(100);
		assertEquals(
				"Test",
				c.getTextArea()
						.getText()
						.substring(
								c.getTextArea().getText().lastIndexOf(']') + 2,
								c.getTextArea().getText().lastIndexOf('t') + 1));
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob eine ueber eine zweite Connection gesendete Nachricht auch bei der ersten ankommt
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testNetworkSend3() throws IOException, InterruptedException {
		nc.initConnection("127.0.0.1", 5469);
		nc.send("Hallo");
		NetworkController nc2 = new NetworkController(c);
		nc2.initConnection("127.0.0.1", 5469);
		nc2.send("Test");
		Thread.sleep(200);
		assertEquals(
				"Test",
				c.getTextArea()
						.getText()
						.substring(
								c.getTextArea().getText().lastIndexOf(']') + 2,
								c.getTextArea().getText().lastIndexOf('t') + 1));
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob eine Nachricht auch ankommt, wenn eine zweite Connection auf und wieder abgebaut wird.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void testNetworkSend4() throws IOException, InterruptedException {
		nc.initConnection("127.0.0.1", 5418);
		NetworkController nc2 = new NetworkController(c);
		nc2.initConnection("127.0.0.1", 5418);
		nc2.closeConnection();
		nc.send("Test");
		Thread.sleep(100);
		assertEquals(
				"Test",
				c.getTextArea()
						.getText()
						.substring(
								c.getTextArea().getText().lastIndexOf(']') + 2,
								c.getTextArea().getText().lastIndexOf('t') + 1));
		nc.closeConnection();
	}
	
	/**
	 * Testen, ob ohne Initialisierung einer Connection eine Exception beim Senden geworfen wird.
	 * @throws IOException
	 */
	@Test (expected=NullPointerException.class)
	public void testNetworkSend5() throws IOException {
		nc.send("Test");
	}

}
