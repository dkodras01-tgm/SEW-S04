package kodrasritter.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kodrasritter.Controller;

@SuppressWarnings("serial")
public class ChatWindow extends JFrame{

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;
	private JButton btnSenden;
	private ActionListener al;
	private JCheckBox chckbxTouppercase, chckbxCensor, chckbxDoubleletter;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatWindow frame = new ChatWindow(new ChatActionListener(new Controller()));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatWindow(ActionListener al) {
		this.al = al;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.addActionListener(al);
		panel.add(btnSenden, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0));
		
		JCheckBox chckbxTouppercase = new JCheckBox("ToUpperCase");
		panel_2.add(chckbxTouppercase);
		//chckbxTouppercase.addItemListener(this);
		
		JCheckBox chckbxCensor = new JCheckBox("Censor");
		panel_2.add(chckbxCensor);
		//chckbxCensor.addItemListener(this);
		
		JCheckBox chckbxDoubleletter = new JCheckBox("DoubleLetter");
		panel_2.add(chckbxDoubleletter);
		//chckbxDoubleletter.addItemListener(this);
		
		textField = new JTextField();
		textField.addActionListener(al);
		panel_1.add(textField);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		
		this.setTitle("Special Chat");
		this.setVisible(true);
		
	}
	
	public void addToTextArea(String content) {
		textArea.append(content);
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String content) {
		textField.setText(content);
	}
	
	public JTextField getTextField() {
		return this.textField;
	}
	
	public JButton getBtnSenden() {
		return this.btnSenden;
	}
	
	public boolean isToUpperCase() {
		if (chckbxTouppercase.isSelected())
			return true;
		return false;
		
	}
	
	public boolean isCensor() {
		if (chckbxCensor.isSelected())
			return true;
		return false;
	}
	
	public boolean isDoubleLetter() {
		if (chckbxDoubleletter.isSelected())
			return true;
		return false;
	}
	
	//@Override
	//public void itemStateChanged(ItemEvent e) {
	//	
	//}
}