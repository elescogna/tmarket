package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Contact extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldSubject;
    private JLabel lblTitle;
    private JLabel lblSubject;
    private JLabel lblBody;
    private JTextArea textAreaBody;
    private JLabel lblTo;
    private JLabel lblItem;
    private JButton btnBack;
    private JButton btnSend;

    /**
     * Create the panel.
     */
    public Contact() {
    	this.setLayout(null);
    	
    	lblTitle = new JLabel("Contact");
    	lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
    	lblTitle.setBounds(179, 12, 97, 26);
    	add(lblTitle);
    	
    	lblSubject = new JLabel("Subject:");
    	lblSubject.setBounds(12, 91, 60, 17);
    	add(lblSubject);
    	
    	textFieldSubject = new JTextField();
    	textFieldSubject.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent e) {
    			// TODO
    		}
    	});
    	textFieldSubject.setBounds(65, 89, 373, 21);
    	add(textFieldSubject);
    	textFieldSubject.setColumns(10);
    	
    	lblBody = new JLabel("Body:");
    	lblBody.setBounds(12, 122, 60, 17);
    	add(lblBody);
    	
    	textAreaBody = new JTextArea();
    	textAreaBody.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent e) {
    			// TODO
    		}
    	});
    	textAreaBody.setBounds(12, 144, 426, 105);
    	add(textAreaBody);
    	
    	lblTo = new JLabel("To:");
    	lblTo.setBounds(12, 33, 60, 17);
    	add(lblTo);
    	
    	lblItem = new JLabel("Item:");
    	lblItem.setBounds(12, 62, 60, 17);
    	add(lblItem);
    	
    	btnBack = new JButton("Back");
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			// TODO
    		}
    	});
    	btnBack.setBounds(113, 261, 105, 27);
    	add(btnBack);
    	
    	btnSend = new JButton("Send");
    	btnSend.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			// TODO
    		}
    	});
    	btnSend.setBounds(226, 261, 105, 27);
    	add(btnSend);}
}
