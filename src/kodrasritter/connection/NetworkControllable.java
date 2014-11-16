package kodrasritter.connection;

public interface NetworkControllable {
	
	public void receive(String content);
	
	public void send(String content);
	
}
