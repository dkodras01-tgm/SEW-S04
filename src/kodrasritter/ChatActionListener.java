package kodrasritter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
