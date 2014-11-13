package kodrasritter.message;

public class Modifizierer implements Nachricht {
	
	private String content;
	private Nachricht component;

	public Modifizierer() {
		
	}
	
	@Override
	public String getContent() {
		return content;
	}
	
}
