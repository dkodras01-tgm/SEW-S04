package kodrasritter.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import kodrasritter.Controller;
import kodrasritter.gui.ChatWindow;

import org.junit.Before;
import org.junit.Test;

/**
 * Testen der GUI
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class TestGUI {
	
	private ChatWindow display;
	private Controller c;
	

	/**
	 * Initialisieren der Attribute
	 * 
	 * @throws IOException Fehler bei der Netzwerkkommunikation
	 */
	@Before
	public void init() throws IOException {
		this.c = new Controller("127.0.0.1", 5642);
		this.display = (ChatWindow) c.getDisplay();
		this.display.setVisible(false);
	}
	
	/**
	 * Testen, ob eine Eingabe richtig abgefragt wird
	 */
	@Test
	public void testEingabe1() {
		display.updateUserInput("Testeingabe");
		assertEquals("Testeingabe", display.getUserInput());
	}
	
	/**
	 * Testen, ob ein leerer Userinput auch ein leerer Input bleibt
	 */
	@Test
	public void testEingabe2() {
		display.updateUserInput("");
		assertEquals("", display.getUserInput());
	}
	
	/**
	 * Testen, ob nach dem Klicken des Senden-Buttons die Eingabe des Users zurueckgesetzt wird
	 */
	@Test
	public void testSenden1() {
		display.updateUserInput("Testeingabe");
		display.getBtnSenden().doClick();
		assertEquals("", display.getUserInput());
	}
	
	/**
	 * Testen, ob eine Eingabe richtig gesendet wird
	 * 
	 * @throws InterruptedException Auftreten eines Interruptes
	 */
	@Test
	public void testSendenEmpfangen1() throws InterruptedException {
		display.updateUserInput("Testeingabe");
		display.getBtnSenden().doClick();
		Thread.sleep(100);
		assertEquals(
				"Test",
				display.getTextArea()
						.getText()
						.substring(
								display.getTextArea().getText().lastIndexOf(']') + 2,
								display.getTextArea().getText().lastIndexOf('t') + 1));
	}
	
	/**
	 * Testen, ob eine Eingabe richtig mit dem Modifier ToUpperCase gesendet wird
	 * 
	 * @throws InterruptedException Auftreten eines Interruptes
	 */
	@Test
	public void testSendenEmpfangen2() throws InterruptedException {
		display.updateUserInput("Test");
		display.getChckbxTouppercase().doClick();
		display.getBtnSenden().doClick();
		Thread.sleep(100);
		assertEquals(
				"TEST",
				display.getTextArea()
						.getText()
						.substring(
								display.getTextArea().getText().lastIndexOf(']') + 2,
								display.getTextArea().getText().lastIndexOf('T') + 1));
	}
	
	/**
	 * Testen, ob eine Eingabe richtig mit zwei Modifiern (ToUpeprCase und DoubleLetter) gesendet wird
	 * 
	 * @throws InterruptedException Auftreten eines Interruptes
	 */
	@Test
	public void testSendenEmpfangen3() throws InterruptedException {
		display.updateUserInput("Test");
		display.getChckbxTouppercase().doClick();
		display.getChckbxDoubleletter().doClick();
		display.getBtnSenden().doClick();
		Thread.sleep(100);
		assertEquals(
				"TTEESSTT",
				display.getTextArea()
						.getText()
						.substring(
								display.getTextArea().getText().lastIndexOf(']') + 2,
								display.getTextArea().getText().lastIndexOf('T') + 1));
	}
	
	/**
	 * Testen, ob eine Eingabe korrekt mit dem Modifier DoubleLetter gesendet wird
	 * 
	 * @throws InterruptedException Auftreten eines Interruptes
	 */
	@Test
	public void testSendenEmpfangen4() throws InterruptedException {
		display.updateUserInput("Test");
		display.getChckbxDoubleletter().doClick();
		display.getBtnSenden().doClick();
		Thread.sleep(100);
		assertEquals(
				"TTeesstt",
				display.getTextArea()
						.getText()
						.substring(
								display.getTextArea().getText().lastIndexOf(']') + 2,
								display.getTextArea().getText().lastIndexOf('t') + 1));
	}
	
	/**
	 * Testen, ob eine Eingabe korrekt mit dem Modifier Censor gesendet wird
	 * 
	 * @throws InterruptedException Auftreten eines Interruptes
	 */
	@Test
	public void testSendenEmpfangen5() throws InterruptedException {
		display.updateUserInput("Shit");
		display.getChckbxCensor().doClick();
		display.getBtnSenden().doClick();
		Thread.sleep(100);
		assertEquals(
				"$%&*",
				display.getTextArea()
						.getText()
						.substring(
								display.getTextArea().getText().lastIndexOf(']') + 2,
								display.getTextArea().getText().lastIndexOf('*') + 1));
	}
	
	/**
	 * Testen, ob die ausgewaehlte Option "DoubleLetter" richtig zurueckgegeben werden
	 * 
	 */
	@Test
	public void testOptions() {
		display.getChckbxDoubleletter().doClick();
		assertEquals("DoubleLetter", display.getOptions().get(0));
	}
	
	/**
	 * Testen, ob die ausgewaehlte Option "ToUpperCase" richtig zurueckgegeben werden
	 * 
	 */
	@Test
	public void testOptions2() {
		display.getChckbxTouppercase().doClick();
		assertEquals("ToUpperCase", display.getOptions().get(0));
	}
	
	/**
	 * Testen, ob die ausgewaehlte Option "Censor" richtig zurueckgegeben werden
	 * 
	 */
	@Test
	public void testOptions3() {
		display.getChckbxCensor().doClick();
		assertEquals("Censor", display.getOptions().get(0));
	}
	
	/**
	 * Testen, ob bei Auswahl einer Option und wieder Deaktivierung dieser Option auch
	 * keine Option zurueckgegeben wird.
	 * 
	 * @throws InterruptedException Auftreten eines Interrupts
	 */
	@Test
	public void testOptions4() throws InterruptedException {
		display.getChckbxCensor().doClick();
		Thread.sleep(10);
		display.getChckbxCensor().doClick();
		assertEquals(0, display.getOptions().size());
	}
	
	/**
	 * Testen, ob bei Aktivierung aller Optionen (ToUpperCase, DoubleLetter, Censor) auch alle zurueckgegeben werden.
	 */
	@Test
	public void testOptions5() {
		display.getChckbxTouppercase().doClick();
		display.getChckbxCensor().doClick();
		display.getChckbxDoubleletter().doClick();
		assertEquals("ToUpperCase", display.getOptions().get(0));
		assertEquals("Censor", display.getOptions().get(1));
		assertEquals("DoubleLetter", display.getOptions().get(2));
	}
	

}
