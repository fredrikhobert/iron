import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 *
 * Chattserver
 * 
 * */

public class Server {

	private UserInterface ui;
	private ServerSocket socket;
	private ArrayList<ClientHandler> clients;


	public Server(int port){
		ui = new UserInterface();
		start(port); 
	}


	// Startar chattserver och väntar på anslutning från chattklienter
	public void start(int port){
		try {
			socket = new ServerSocket(port);
			clients = new ArrayList<ClientHandler>();
			setTitle();
			
			while(true){
				Socket clientSocket = socket.accept();
				ClientHandler client = new ClientHandler(clientSocket, this);
				clients.add(client);
				broadcastMsg(client.getHost(), "* Connected *");
				setTitle();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void setTitle(){
		try {
			ui.setStatusTitle("Chat-Server. "
					+ " Host: " + socket.getInetAddress().getLocalHost().getHostName() 
					+ " Port: " + socket.getLocalPort() 
					+ " Clients: " + clients.size());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	// Sänder ut meddelande till alla anslutna chattklienter
	public synchronized void broadcastMsg(String host, String message){
		message = host + ": " + message;
		ui.printMessage(message);
		for(ClientHandler client : clients){
			if(!client.printMessage(message))
				killThread(client);
		}
	}
	
	
	public void killThread(ClientHandler client){
		clients.remove(client);
		broadcastMsg(client.getHost(), "* Disconnected *");
		setTitle();
	}


	public static void main(String[] args){
		
		if(args.length == 1)
			new Server(Integer.parseInt(args[0]));
		else
			new Server(2000);
	}
}
