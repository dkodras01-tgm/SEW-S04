package kodrasritter.message;

public class UpperCase extends Modifier {
	
	
	public UpperCase(Message m) {
		super(m);
	}
	
	public void process() {
		super.setContent(this.contentUppercase(super.getMessage().getContent()));
	}
	
	private String contentUppercase(String content) {
		return content.toUpperCase();
	}

}
