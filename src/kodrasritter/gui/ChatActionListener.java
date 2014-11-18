package kodrasritter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import kodrasritter.Controller;


public class ChatActionListener implements 
		ActionListener, WindowListener, ItemListener {
	
	Controller control;
	
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


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
