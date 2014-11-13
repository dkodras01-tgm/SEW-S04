package kodrasritter.message;

public class UpperCase extends Modifier {
	
	Message m;
	
	public UpperCase(Message m) {
		this.m = m;
	}
	
	public String process() {
		return contentUppercase(m.process());
	}
	
	private String contentUppercase(String content) {
		return content.toUpperCase();
	}

}
