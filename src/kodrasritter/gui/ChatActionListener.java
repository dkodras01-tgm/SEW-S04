package kodrasritter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import kodrasritter.Controller;

/**
 * Diese Klasse implementiert einen {@link ActionListener} und einen {@link WindowListener}
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class ChatActionListener implements ActionListener, WindowListener {
	
	private Controller control;
	
	/**
	 * Controller, von dem aus die weitere Verarbeitung nach Ausloesung eines Events stattfindet.
	 * 
	 * @param control Controller
	 */
	public ChatActionListener(Controller control) {
		this.control = control;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		control.send();	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		control.closeConnection();
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}


}
