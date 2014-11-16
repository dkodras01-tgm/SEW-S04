package kodrasritter.connection;

import java.io.IOException;

public interface Sendable {
	
	public void send(String content) throws IOException;

}
