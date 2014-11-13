package kodrasritter.message;

public class DoubleCharacter extends Modifier {

	
	public DoubleCharacter(Message m) {
		super(m);
	}
	
	public void process() {
		super.setContent(this.doubleCharacters(super.getMessage().getContent()));
	}
	
	public String doubleCharacters(String content) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<content.length(); i++)
			for (int k=0; k<2; k++)
				sb.append(content.charAt(i));
		
		return sb.toString();
		
	}

}
