package kodrasritter.message;

public class ChatMessage extends Message {

	@Override
	public String process() {
		return super.getContent();
	}



}
