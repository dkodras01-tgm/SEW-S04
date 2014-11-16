package kodrasritter.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kodrasritter.Controller;

public class ChatActionListener implements ActionListener {
	
	Controller control;
	
	public ChatActionListener(Controller control) {
		this.control = control;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		control.send();
		
	}

}
