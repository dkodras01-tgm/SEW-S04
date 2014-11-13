package kodrasritter.message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Censorship extends Modifier{

	private Message m;
	
	private HashMap<String, String> change;
	
	public Censorship(Message m) {
		this.m = m;
		change = new HashMap<String, String>();
	}
	
	public void addCensoredWords(String censoredWord, String newWord) {
		change.put(censoredWord, newWord);
	}
	
	public void removeCensoredWords(String censoredWord) {
		change.remove(censoredWord);
	}
	
	public void process() {
		super.setContent(this.censor(m.getContent()));
	}
	
	public String censor(String content) {
		
		List<String> temp = Arrays.asList(content.split(" "));
		
		for (int i=0; i<temp.size(); i++) {
			if (change.containsKey(temp)) {
				temp.set(i, change.get(temp));
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
	
}
