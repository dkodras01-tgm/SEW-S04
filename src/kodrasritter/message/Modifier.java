package kodrasritter.message;

public abstract class Modifier extends Message {

	private Message m;
	
	public Modifier(Message m) {
		this.m = m;
	}
	
	@Override
	public String getContent() {
		return super.getContent();
		
	}

	@Override
	public void setContent(String content) {
		super.setContent(content);
		
	}
	

	public Message getMessage() {
		return this.m;
	
	}

	
	public abstract void process();
	

}
