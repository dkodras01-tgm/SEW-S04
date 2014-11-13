package kodrasritter.test;

import kodrasritter.message.*;

public class Test {
	
	public static void main(String[] args) {
		Message cm = new ChatMessage();
		cm.setContent("Hallo wie gehts");
		
		cm = new DoubleCharacter(cm);
		cm = new DoubleCharacter(cm);
		cm = new UpperCase(cm);
		
		
		cm.setContent(cm.process());
		
		System.out.println(cm.getContent());
		
	}

}
