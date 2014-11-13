package kodrasritter.message;

public abstract class Message {
	
	private String content;
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public abstract String process();

}
