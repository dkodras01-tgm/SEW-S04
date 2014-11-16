package kodrasritter.gui;

import kodrasritter.*;

public class Display implements Displayable {

	Controller control;
	ChatWindow cw;
	
	public Display(ChatWindow cw) {
		this.cw = cw;
	}
	
	@Override
	public void updateInputDisplay(String content) {
		cw.addToTextArea(content);
		cw.addToTextArea("\n");
	}

	public void updateUserInput(String content) {
		cw.setText(content);
	}
	
	public String getUserInput() {
		return cw.getText();
	}
	

}
