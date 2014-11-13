package kodrasritter.message;

public class UpperCase extends Modifier {
	
	private Message m;
	
	public UpperCase(Message m) {
		this.m = m;
	}
	
	public void process() {
		super.setContent(this.contentUppercase(m.getContent()));
	}
	
	private String contentUppercase(String content) {
		return content.toUpperCase();
	}

}
