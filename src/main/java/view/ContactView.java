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
        this.setLayout(null);

        this.contactViewModel = contactViewModel;
        this.contactController = contactController;
        this.goHomeController = goHomeController;

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
                ContactState currentState =
                    ContactView.this.contactViewModel.getState();
                currentState.setSubjectText(textFieldSubject.getText() +
                        e.getKeyChar());
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
                ContactState currentState =
                    ContactView.this.contactViewModel.getState();
                currentState.setBodyText(textAreaBody.getText() + e.getKeyChar());
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
                ContactView.this.goHomeController.execute();
            }
        });
        btnBack.setBounds(113, 261, 105, 27);
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
        btnSend.setBounds(226, 261, 105, 27);
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
