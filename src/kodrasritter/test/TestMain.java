package kodrasritter.test;

import static org.junit.Assert.*;
import kodrasritter.Main;

import org.junit.Test;

public class TestMain {

	/**
	 * Testen der Argumente: Statt IP Buchstaben verwendet
	 */
	@Test
	public void testArgs() {
		String args = "blablabla";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Statt IP nichts verwendet
	 */
	@Test
	public void testArgs2() {
		String args = "";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Statt IP nur eine Zahl verwendet
	 */
	@Test
	public void testArgs3() {
		String args = "34535";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Statt IP nur eine Zahl verwendet + Port
	 */
	@Test
	public void testArgs4() {
		String args = "34535 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs5() {
		String args = "345.35 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs6() {
		String args = "bls.sd 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs7() {
		String args = "0.0.0.0 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs8() {
		String args = "239.256.0.0 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs9() {
		String args = "254.256.0.0 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: Inkorrekte IP + Port
	 */
	@Test
	public void testArgs10() {
		String args = "224.-1.0.0 4534";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + inkorrekter Port
	 */
	@Test
	public void testArgs11() {
		String args = "239.34.3.2 bla";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + inkorrekter Port
	 */
	@Test
	public void testArgs12() {
		String args = "239.34.3.2 -1";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + inkorrekter Port
	 */
	@Test
	public void testArgs13() {
		String args = "239.34.3.2 66000";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + Port + inkorrekte TTL
	 */
	@Test
	public void testArgs14() {
		String args = "239.34.3.2 34458 bla";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + Port + inkorrekte TTL
	 */
	@Test
	public void testArgs15() {
		String args = "239.34.3.2 34458 0";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + Port + inkorrekte TTL
	 */
	@Test
	public void testArgs16() {
		String args = "239.34.3.2 34458 256";
		assertEquals(false, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + Port + TTL
	 */
	@Test
	public void testArgs17() {
		String args = "239.34.3.2 34458 1";
		assertEquals(true, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: IP + Port
	 */
	@Test
	public void testArgs18() {
		String args = "239.34.3.2 34458";
		assertEquals(true, Main.checkArgs(args.split(" ")));
	}
	
	/**
	 * Testen der Argumente: keines
	 */
	@Test
	public void testArgs19() {
		String[] args = {};
		assertEquals(true, Main.checkArgs(args));
	}

}
