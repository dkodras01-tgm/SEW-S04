package kodrasritter.message;

public abstract class Modifier extends Message {

	

	
	@Override
	public String getContent() {
		return super.getContent();
		
	}

	@Override
	public void setContent(String content) {
		super.setContent(content);
		
	}
	
	public abstract String process();
	

}
