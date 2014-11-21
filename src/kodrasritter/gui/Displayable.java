package kodrasritter.gui;

import java.util.List;

/**
 * Dieses Interface gibt Methoden zur Verbindung zwischen Chat-Anzeige und Verarbeitung vor.
 * 
 * @author Dominik Kodras
 * @author Mathias Ritter
 * @version 1.0
 */
public interface Displayable {

	/**
	 * In dieser Methode wird das Display, das heisst der Bereich, der zur Darstellung
	 * der Chatnachrichten verwendet wird, geupdated.<br>
	 * Dabei wird der angegebene Content (Nachricht) zur Anzeige hinzugefuegt
	 * 
	 * @param content Inhalt einer Nachricht
	 */
	public void updateChatDisplay(String content);
	
	/**
	 * In dieser Methode wird ein Text fuer das Texteingabefeld des Users festgelegt.
	 * 
	 * @param content Inhalt des Eingabefeldes
	 */
	public void updateUserInput(String content);
	
	
	/**
	 * Diese Methode gibt den Inhalt des Benutzereingabefeldes als String zurueck.
	 * 
	 * @return Eingabe des Users
	 */
	public String getUserInput();
	
	/**
	 * Mit Hilfe dieser Methode wird der Titel der Chat-Anzeige gesetzt.
	 * 
	 * @param content Der einzustellende Titel
	 */
	public void setDisplayTitle(String content);
	
	
	/**
	 * Diese Methode gibt die vom User gewuenschten Chat-Optionen zurueck. <br>
	 * Z.B. DoubleLetter, DoubleCharacter etc.
	 * 
	 * @return Die vom User gewuenschten Chat-Optionen
	 */
	public List<String> getOptions();

}
