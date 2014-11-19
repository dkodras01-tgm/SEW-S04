package kodrasritter.message;

public abstract class Modifier extends Message {

	private Message m;
	
	public Modifier(Message m) {
		this.setMessage(m);
	}
	

	public Message getMessage() {
		return m;
	}

	public void setMessage(Message m) {
		this.m = m;
	}
	
	public abstract String process();
	

}
