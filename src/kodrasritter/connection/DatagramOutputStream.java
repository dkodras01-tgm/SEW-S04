package kodrasritter.connection;

import java.io.*;
import java.net.*;

/**
 * Dieser OutputStream wandelt die Daten eines Streams in ein DatagramPacket um, um dieses dann zu versenden.
 * 
 * Diese Klasse wurde mit wenigen Aenderungen von folgender Seite uerbernommen:
 * http://www.javaworld.com/article/2076519/learn-java/multicast-the-chatwaves.html
 * [abgerufen am 16.11.2014]
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public class DatagramOutputStream extends ByteArrayOutputStream {
	DatagramSocket socket;
	DatagramPacket packet;

	public DatagramOutputStream(DatagramSocket socket, InetAddress address,
			int port) {
		this(socket, address, port, 512);
	}

	public DatagramOutputStream(DatagramSocket socket, InetAddress address,
			int port, int initialSize) {
		super(initialSize);
		this.socket = socket;
		packet = new DatagramPacket(buf, 0, address, port);
	}

	@Override
	public synchronized void flush() throws IOException {
		if (count >= 65508)
			throw new IOException("Packet overflow (" + count + ") bytes");
		packet.setData(buf, 0, count);
		socket.send(packet);
		reset();
	}
}
