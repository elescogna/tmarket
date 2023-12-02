package view;

import interface_adapter.contact.ContactController;
import interface_adapter.contact.ContactState;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.go_home.GoHomeController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;

public class ContactView extends JPanel implements PropertyChangeListener {

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

    private ContactViewModel contactViewModel;
    private ContactController contactController;
    private GoHomeController goHomeController;

    /**
     * Create the panel.
     */
    public ContactView(ContactViewModel contactViewModel,
            ContactController contactController,
            GoHomeController goHomeController) {
    	setBackground(new Color(0, 0, 0));
        this.setLayout(null);

        this.contactViewModel = contactViewModel;
        this.contactController = contactController;
        this.goHomeController = goHomeController;

        lblTitle = new JLabel("Contact");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 26));
        lblTitle.setBounds(451, 115, 97, 26);
        add(lblTitle);

        lblSubject = new JLabel("Subject:");
        lblSubject.setForeground(new Color(255, 255, 255));
        lblSubject.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblSubject.setBounds(80, 276, 60, 17);
        add(lblSubject);

        textFieldSubject = new JTextField();
        textFieldSubject.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ContactState currentState =
                    ContactView.this.contactViewModel.getState();
                currentState.setSubjectText(textFieldSubject.getText() +
                        e.getKeyChar());
            }
        });
        textFieldSubject.setBounds(80, 326, 816, 21);
        add(textFieldSubject);
        textFieldSubject.setColumns(10);

        lblBody = new JLabel("Body:");
        lblBody.setForeground(new Color(255, 255, 255));
        lblBody.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblBody.setBounds(83, 386, 119, 17);
        add(lblBody);

        textAreaBody = new JTextArea();
        textAreaBody.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ContactState currentState =
                    ContactView.this.contactViewModel.getState();
                currentState.setBodyText(textAreaBody.getText() + e.getKeyChar());
            }
        });
        textAreaBody.setBounds(83, 432, 813, 197);
        add(textAreaBody);

        lblTo = new JLabel("To:");
        lblTo.setForeground(new Color(255, 255, 255));
        lblTo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblTo.setBounds(80, 187, 60, 17);
        add(lblTo);

        lblItem = new JLabel("Item:");
        lblItem.setForeground(new Color(255, 255, 255));
        lblItem.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblItem.setBounds(80, 231, 60, 17);
        add(lblItem);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ContactView.this.goHomeController.execute();
            }
        });
        btnBack.setBounds(221, 702, 105, 27);
        add(btnBack);

        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ContactState currentState =
                    ContactView.this.contactViewModel.getState();

                ContactView.this.contactController.execute(
                        currentState.getCurrentItem(), currentState.getSubjectText(),
                        currentState.getBodyText());
            }
        });
        btnSend.setBounds(586, 702, 105, 27);
        add(btnSend);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // two situations: error is null (flash success message) or error is not
        // (flash error message)
        ContactState currentState = (ContactState)e.getNewValue();

        if (currentState.getError() == null) {
            JOptionPane.showMessageDialog(this, "Message sent successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send message.");
        }
    }
}
