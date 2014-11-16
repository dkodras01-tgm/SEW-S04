package kodrasritter.connection;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Sender implements Sendable {

	OutputStreamWriter out;

	public Sender(OutputStreamWriter out) {
		this.out = out;
	}

	@Override
	public void send(String content) throws IOException {
		out.write(content + "\n");
		out.flush();		
	}
	
}
