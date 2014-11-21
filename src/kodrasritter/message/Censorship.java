package kodrasritter.message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Diese Klasse ersetzt boese Woerter in der Nachricht.
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class Censorship extends Modifier{

//	HashMap<String, String> change;
	private ArrayList<String> change;

	/**
	 * Im Konstruktor wird der super-Konstrukter aufgerufen
	 * 
	 * @param m
	 * @throws IOException 
	 */
	public Censorship(Message m) throws IOException {
		super(m);
		this.change = new ArrayList<String>();
		this.addCensoredWords();
	}
	
	/**
	 * Zu zensiertende Worte hinzufuegen aus badwords.txt
	 * 
	 * @throws IOException 
	 */
	public void addCensoredWords() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("./resources/badwords.txt", "r");
		String line;
		while ((line = raf.readLine())!=null) {
			change.add(line.toLowerCase());
			line = null;
		}
		raf.close();
	}
	
	/** 
	 * Zensur wird ausgefuehrt
	 * 
	 * @return zensierte Nachricht
	 */
	public String process() {
		return censor(getMessage().process());
	}
	
	/**
	 * Woerter in der Nachricht werden Zensiert
	 * 
	 * @param content
	 * @return zensierte Nachricht
	 */
	public String censor(String content) {
		
		
		List<String> temp = Arrays.asList(content.toLowerCase().split(" "));
		
		for (int i=0; i<temp.size(); i++) {
			for (int k=0; k<change.size(); k++) {
				if (temp.get(i).equalsIgnoreCase(change.get(k))) {
					temp.set(i, "$%&*");
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		String trenner = "";
		for (String act : temp) {
			sb.append(trenner + act);
			trenner = " ";
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> getChange() {
		return change;
	}
	
}
