package kodrasritter.message;

public class ChatMessage implements Message {
	
	
	private String content;
	
	
	@Override
	public String getContent() {
		return content;
		
	}

	@Override
	public void setContent(String content) {
		this.content = content;
		
	}


}
