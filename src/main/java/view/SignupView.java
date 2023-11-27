package view;

import interface_adapter.post.PostState;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTextField uoftEmailTextField;
    private JTextField pickupAddressTextField;
    private JTextField passwordTextField;
    private JTextField repeatPasswordTextField;
    private final SignupController signupController;
    private final SignupViewModel signupViewModel;

    /**
     * Create the panel.
     */
    public SignupView(SignupController controller, SignupViewModel signupViewModel) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;

        this.signupViewModel.addPropertyChangeListener(this);

        setBackground(new Color(197, 159, 247));
        this.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(58, 70, 49, 14);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Uoft Email");
        lblNewLabel_1.setBounds(58, 102, 68, 14);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Home Address");
        lblNewLabel_1_1.setBounds(58, 137, 82, 14);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Password");
        lblNewLabel_1_1_1.setBounds(58, 175, 82, 14);
        add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Repeat Password");
        lblNewLabel_1_1_1_1.setBounds(58, 210, 82, 14);
        add(lblNewLabel_1_1_1_1);

        nameTextField = new JTextField();
        nameTextField.setBounds(225, 67, 96, 20);
        add(nameTextField);
        nameTextField.setColumns(10);

        nameTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        uoftEmailTextField = new JTextField();
        uoftEmailTextField.setColumns(10);
        uoftEmailTextField.setBounds(225, 99, 96, 20);
        add(uoftEmailTextField);

        uoftEmailTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setUoftEmail(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        pickupAddressTextField = new JTextField();
        pickupAddressTextField.setColumns(10);
        pickupAddressTextField.setBounds(225, 134, 96, 20);
        add(pickupAddressTextField);

        pickupAddressTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setPickupAddress(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordTextField = new JTextField();
        passwordTextField.setColumns(10);
        passwordTextField.setBounds(225, 172, 96, 20);
        add(passwordTextField);

        passwordTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setPassword(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        repeatPasswordTextField = new JTextField();
        repeatPasswordTextField.setColumns(10);
        repeatPasswordTextField.setBounds(225, 207, 96, 20);
        add(repeatPasswordTextField);

        repeatPasswordTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setRepeatPassword(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        JButton signUp = new JButton("Sign Up");
        signUp.setBounds(323, 253, 89, 23);
        add(signUp);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getPickupAddress(),
                                    currentState.getUoftEmail()
                            );
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Error");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }
        }
    }
}
