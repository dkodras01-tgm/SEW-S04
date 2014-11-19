package kodrasritter.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import kodrasritter.Controller;

@SuppressWarnings("serial")
public class ChatWindow extends JFrame implements Displayable {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;
	private JButton btnSenden;
	private JCheckBox chckbxTouppercase, chckbxCensor, chckbxDoubleletter;

	/**
	 * Create the frame.
	 */
	public ChatWindow(ActionListener al, String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnSenden = new JButton("Senden");
		btnSenden.addActionListener(al);
		panel.add(btnSenden, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1));
		
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0));
		
		chckbxTouppercase = new JCheckBox("ToUpperCase");
		panel_2.add(chckbxTouppercase);
		//chckbxTouppercase.addItemListener(this);
		
		chckbxCensor = new JCheckBox("Censor");
		panel_2.add(chckbxCensor);
		//chckbxCensor.addItemListener(this);
		
		chckbxDoubleletter = new JCheckBox("DoubleLetter");
		panel_2.add(chckbxDoubleletter);
		//chckbxDoubleletter.addItemListener(this);
		
		textField = new JTextField();
		textField.addActionListener(al);
		panel_1.add(textField);
		
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
//		contentPane.add(textArea, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		
		this.setTitle(title);
		this.setVisible(true);
		
	}
	
	/**
	 * @see Displayable#updateChatDisplay(String)
	 */
	@Override
	public void updateChatDisplay(String content) {
		String timeStamp = new SimpleDateFormat("[dd.MM.yyyy - HH:mm:ss]").format(Calendar.getInstance().getTime());
		this.textArea.append(timeStamp + " " + content);
		this.textArea.append("\n");
		this.scrollToBottom();
		
	}

	/**
	 * @see Displayable#updateUserInput(String)
	 */
	@Override
	public void updateUserInput(String content) {
		this.textField.setText(content);	
	}

	/**
	 * @see Displayable#getUserInput()
	 */
	@Override
	public String getUserInput() {
		return this.textField.getText();
	}
	
	/**
	 * @see Displayable#getOptions()
	 */
	@Override
	public List<String> getOptions() {
		LinkedList<String> list = new LinkedList<String>();
		
		if (chckbxTouppercase.isSelected())
			list.add("ToUpperCase");
		
		if (chckbxCensor.isSelected())
			list.add("Censor");
		
		if (chckbxDoubleletter.isSelected())
			list.add("DoubleLetter");
		
		return list;
	}
	
	/**
	 * Diese Methode scrollt ans Ende der textArea.<br>
	 * Sie wird immer nach dem Empfangen einer neuen Nachricht ({@link #updateChatDisplay(String)}
	 * aufgerufen.
	 */
	private void scrollToBottom() {
		   
		int endPosition = this.textArea.getDocument().getLength();
		Rectangle bottom;
		try {
			bottom = this.textArea.modelToView(endPosition);
			this.textArea.scrollRectToVisible(bottom);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	     
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	
	
	public JButton getBtnSenden() {
		return btnSenden;
	}

	public JCheckBox getChckbxTouppercase() {
		return chckbxTouppercase;
	}


	public JCheckBox getChckbxCensor() {
		return chckbxCensor;
	}


	public JCheckBox getChckbxDoubleletter() {
		return chckbxDoubleletter;
	}

	
	
	
}