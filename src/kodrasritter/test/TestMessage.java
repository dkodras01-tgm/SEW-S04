package kodrasritter.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import kodrasritter.message.Censorship;
import kodrasritter.message.ChatMessage;
import kodrasritter.message.DoubleCharacter;
import kodrasritter.message.Message;
import kodrasritter.message.UpperCase;

import org.junit.Test;

/**
 * Testen der Messages (Decorator-Pattern)
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class TestMessage {

	/**
	 * Testen ob alle Buchstaben gorssgeschrieben sind
	 */
	@Test
	public void testContentUpperCase() {
		Message m = new ChatMessage();
		UpperCase uc = new UpperCase(m);
		m.setContent("blablabla");
		assertEquals("BLABLABLA", uc.process());
	}
	
	/**
	 * Testen ob alle Buchstaben doppelt zurueckgegeben werden
	 */
	@Test
	public void testDoubleCharacter2() {
		Message m = new ChatMessage();
		DoubleCharacter dc = new DoubleCharacter(m);
		m.setContent("&%!");
		assertEquals("&&%%!!", dc.process());
	}
	
	/**
	 * Testen ob alle Buchstaben doppelt zurueckgegeben werden
	 */
	@Test
	public void testDoubleCharacter() {
		Message m = new ChatMessage();
		DoubleCharacter dc = new DoubleCharacter(m);
		m.setContent("Bla");
		assertEquals("BBllaa", dc.process());
	}
	
	/**
	 * Testen ob alle boesen Woerter zensiert werden
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	@Test
	public void testCensor1() throws IOException {
		Message m = new ChatMessage();
		m.setContent("shit");
		m = new Censorship(m);
		m.setContent(m.process());
		assertEquals("$%&*", m.getContent());
	}
	
	/**
	 * Testen ob alle boesen Woerter zensiert werden
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	@Test
	public void testCensor2() throws IOException {
		Message m = new ChatMessage();
		m.setContent("shit fuck");
		m = new Censorship(m);
		m.setContent(m.process());
		assertEquals("$%&* $%&*", m.getContent());
	}
	
	/**
	 * Testen ob alle boesen Woerter zensiert werden
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	@Test
	public void testCensor3() throws IOException {
		Message m = new ChatMessage();
		m.setContent("hallo fuck");
		m = new Censorship(m);
		m.setContent(m.process());
		assertEquals("hallo $%&*", m.getContent());
	}
	
	/**
	 * Testen ob alle boesen Woerter zensiert werden
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	@Test
	public void testCensor4() throws IOException {
		Message m = new ChatMessage();
		m.setContent("hallo");
		m = new Censorship(m);
		m.setContent(m.process());
		assertEquals("hallo", m.getContent());
	}
	
	
	
}
