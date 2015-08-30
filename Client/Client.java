import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 * Chattklient
 * */

public class Client extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private UserInterface ui;
	private String id;


	public Client(String host, int port) {
		ui = new UserInterface(this);
		setId();
		if(!connect(host, port))
			System.exit(0);
		else
			ui.setTitle(host, port);

		start();
	}


	// Anslut till chattserver
	private boolean connect(String host, int port){
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"ISO-8859-1" ), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	// Läser in meddelanden från chattserver
	public void run(){
		while(true){
			String message = null;
			try {
				message = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			ui.receiveMessage(message);
		}
	}


	// Skicka meddelande till chattserver
	public void sendMessage(String message){
		if(message != "")
			out.println(id + ": " + message);
	}


	// Enkelt id för att hålla reda på författare till meddelanden 
	private void setId(){
		int number = (int) (Math.random() * 10000);
		id = "Client" + number;
	}


	public static void main(String[] args) {

		if(args.length == 0){
			new Client("127.0.0.1", 2000);
		} else if(args.length == 1){
			new Client(args[0], 2000);
		} else if(args.length == 2){
			new Client(args[0], Integer.parseInt(args[1]));
		}
	}

}
