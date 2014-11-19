package kodrasritter.message;

public class UpperCase extends Modifier {
	
	
	public UpperCase(Message m) {
		super(m);
	}
	
	public String process() {
		return contentUppercase(getMessage().process());
	}
	
	private String contentUppercase(String content) {
		return content.toUpperCase();
	}

}
