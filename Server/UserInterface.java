import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Fredrik Hobert <fredrik.hobert@gmail.com>
 *
 * Grafisk användargränssnitt för server
 * 
 * */

public class UserInterface extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	JTextArea textarea;
	
	
	public UserInterface(){

		setTitle("Server");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		textarea = new JTextArea();
		add(textarea);
		setVisible(true);
	}
	
	
	public void setStatusTitle(String title){
		setTitle(title);
	}
	
	
	public void printMessage(String message){
		textarea.append(message + "\n");
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
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


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
	
}
