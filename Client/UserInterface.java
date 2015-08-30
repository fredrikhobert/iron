import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 *
 * Grafiskt användargränssnitt för chattklient
 * 
 * */

public class UserInterface extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private JButton send;
	private JTextField textfield;
	private JTextArea textarea;
	private Client client;


	public UserInterface(Client client){

		this.client = client;
		send = new JButton("Skicka!");
		textfield = new JTextField(40);
		textarea = new JTextArea(10, 40);

		setTitle("Chat-Client. ");
		setSize(460,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(new FlowLayout());
		add(new JLabel("Chat: "));
		add(textarea);
		add(new JLabel("Meddelande: "));
		add(textfield);
		send.addActionListener(this);
		add(send);

		setVisible(true);
	}


	public void receiveMessage(String message){
		textarea.append(message + "\n");
	}


	public void actionPerformed(ActionEvent e) {
		client.sendMessage(textfield.getText());
		textfield.setText("");
	}

	
	public void setTitle(String host, int port){
		setTitle("Chat-Client. " + " Host: " + host + " Port: " + port);
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);	
	}
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

}
