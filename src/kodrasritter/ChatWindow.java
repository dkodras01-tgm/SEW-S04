package kodrasritter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

	private JPanel contentPane;
	private TextArea textArea;
	private TextField textField;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChatWindow frame = new ChatWindow();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ChatWindow(ActionListener al) {
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
		
		JCheckBox chckbxCensor = new JCheckBox("Censor");
		panel_2.add(chckbxCensor);
		
		JCheckBox chckbxDubbleletter = new JCheckBox("DubbleLetter");
		panel_2.add(chckbxDubbleletter);
		
		textField = new TextField();
		textField.addActionListener(al);
		panel_1.add(textField);
		
		textArea = new TextArea();
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

}