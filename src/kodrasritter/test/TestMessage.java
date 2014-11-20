package kodrasritter.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import kodrasritter.message.Censorship;
import kodrasritter.message.ChatMessage;
import kodrasritter.message.DoubleCharacter;
import kodrasritter.message.Message;
import kodrasritter.message.UpperCase;

import org.junit.Test;

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
	public void testDoubleCharacter() {
		Message m = new ChatMessage();
		DoubleCharacter dc = new DoubleCharacter(m);
		m.setContent("bla");
		assertEquals("bbllaa", dc.process());
	}
	
	/**
	 * Testen ob alle boesen Woerter in die ArrayList eingelesen werden
	 * @throws IOException 
	 */
	@Test
	public void testAddCensoredWords() throws IOException {
		boolean success = false;
		Message m = new ChatMessage();
		Censorship c = new Censorship(m);
		c.addCensoredWords();
		
		ArrayList<String> al1 = new ArrayList<String>();
		ArrayList<String> al2 = new ArrayList<String>();
		RandomAccessFile raf = new RandomAccessFile("./recources/badwords.txt", "r");
		String line="";
		while ((line = raf.readLine())!=null) {
			al1.add(line);
			line="";
		}
		raf.close();
		al2 = c.getChange();
		if(al1==al2)
			success = true;
		System.out.println(success);
		assertTrue(success);
	}
	
	/**
	 * Testen ob das Enfernen von einem Wort aus der ArrayList funktioniert
	 * @throws IOException 
	 */
	@Test
	public void testRemoveCensoredWords() throws IOException {
		Message m = new ChatMessage();
		Censorship c = new Censorship(m);
		c.addCensoredWords();
		String s;
		c.removeCensoredWords(s = c.getChange().iterator().next());
		assertNotSame(s, c.getChange().iterator().next());
	}
	
}
