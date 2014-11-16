package kodrasritter;

import kodrasritter.*;

public class Display implements Displayable {

	Controller control;
	ChatWindow cw;
	
	public Display(ChatWindow cw) {
		this.cw = cw;
	}
	
	@Override
	public void updateOutput(String content) {
		cw.addToTextArea(content);
		cw.addToTextArea("\n");
	}

	public void updateInput(String content) {
		cw.setText(content);
	}
	
	public String getInput() {
		return cw.getText();
	}
	

}
