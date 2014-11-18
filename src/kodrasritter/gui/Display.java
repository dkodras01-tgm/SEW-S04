package kodrasritter.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import kodrasritter.Controller;

public class Display implements Displayable {

	Controller control;
	ChatWindow cw;
	
	public Display(ChatWindow cw) {
		this.cw = cw;
	}
	
	@Override
	public void updateChatDisplay(String content) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy - HH:mm:ss]").format(Calendar.getInstance().getTime());
		cw.addToTextArea(timeStamp + " " + content);
		cw.addToTextArea("\n");
	}

	public void updateUserInput(String content) {
		cw.setText(content);
	}
	
	public String getUserInput() {
		return cw.getText();
	}
	

}
