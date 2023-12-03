package view;

import interface_adapter.post.PostState;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JTextField uoftEmailTextField;
    private JTextField pickupAddressTextField;
    private JTextField passwordTextField;
    private JTextField repeatPasswordTextField;
    private final SignupController signupController;
    private final SignupViewModel signupViewModel;
    private Image backgroundImage;

    /**
     * Create the panel.
     */
    public SignupView(SignupController controller, SignupViewModel signupViewModel) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;

        this.signupViewModel.addPropertyChangeListener(this);

        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/background_image.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBackground(new Color(0, 0, 0));
        this.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(289, 296, 49, 14);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Uoft Email");
        lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(289, 344, 116, 14);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Home Address");
        lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setBounds(289, 389, 116, 14);
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Password");
        lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1_1.setBounds(289, 439, 82, 14);
        add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Repeat Password");
        lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1_1_1.setBounds(289, 485, 116, 14);
        add(lblNewLabel_1_1_1_1);

        nameTextField = new JTextField();
        nameTextField.setBounds(568, 295, 187, 20);
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
        uoftEmailTextField.setBounds(568, 343, 187, 20);
        add(uoftEmailTextField);

        uoftEmailTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = uoftEmailTextField.getText() + e.getKeyChar();
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
        pickupAddressTextField.setBounds(568, 388, 187, 20);
        add(pickupAddressTextField);

        pickupAddressTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = pickupAddressTextField.getText() + e.getKeyChar();
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
        passwordTextField.setBounds(568, 438, 187, 20);
        add(passwordTextField);

        passwordTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = passwordTextField.getText() + e.getKeyChar();
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
        repeatPasswordTextField.setBounds(568, 484, 187, 20);
        add(repeatPasswordTextField);

        repeatPasswordTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = repeatPasswordTextField.getText() + e.getKeyChar();
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
        signUp.setBounds(455, 578, 89, 23);
        add(signUp);
        
        JLabel lblNewLabel_2 = new JLabel("Sign Up");
        lblNewLabel_2.setFont(new Font("Modern No. 20", Font.BOLD, 26));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(447, 193, 106, 58);
        add(lblNewLabel_2);

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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
