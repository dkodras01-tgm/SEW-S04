package kodrasritter.message;

public class DoubleCharacter extends Modifier {

	Message m;
	
	public DoubleCharacter(Message m) {
		this.m = m;
	}
	
	public String process() {
		return doubleCharacters(m.process());
	}
	
	public String doubleCharacters(String content) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<content.length(); i++)
			for (int k=0; k<2; k++)
				sb.append(content.charAt(i));
		
		return sb.toString();
		
	}

}
