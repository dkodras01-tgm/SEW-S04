package kodrasritter.connection;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Diese Klasse sendet Strings, indem diese in einen Stream geschrieben werden.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class Sender implements Sendable {

	OutputStreamWriter out;

	public Sender(OutputStreamWriter out) {
		this.out = out;
	}

	@Override
	public void send(String content) {
		try {
			out.write(content + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
}
