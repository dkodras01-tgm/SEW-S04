package kodrasritter.gui;

import java.util.List;

public interface Displayable {

	public void updateChatDisplay(String content);
	public void updateUserInput(String content);
	public String getUserInput();
	public List<String> getOptions();

}
