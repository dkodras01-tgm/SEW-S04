package kodrasritter.message;

public abstract class Modifier implements Message {

	private String content;
	
	@Override
	public String getContent() {
		return content;
		
	}

	@Override
	public void setContent(String content) {
		this.content = content;
		
	}
	
	public abstract void process();
	
	

}
