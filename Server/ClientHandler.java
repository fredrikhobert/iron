import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 *
 * Hanterar chattklienter som anslutit till servern
 * 
 * */

public class ClientHandler extends Thread {

	private Server server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String host;


	public ClientHandler(Socket socket, Server server){
		this.server = server;
		this.socket = socket;
		host = socket.getInetAddress().getHostName();

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	
	// Lyssnar efter meddelanden från chattklient för att skicka vidare till broadcast
	public void run(){
		String message;
		try {
			while((message = in.readLine()) != null){	
				server.broadcastMsg(host, message);
			}
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.killThread(this);
	}


	// Skickar meddelande till chattklient
	public boolean printMessage(String message){
		if(socket.isConnected()){
			out.println(message);
			return true;
		} else
			return false;
	}
	
	
	public String getHost(){
		return host;
	}

}
