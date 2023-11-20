package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

	public final String viewName = "log in";
	private final LoginViewModel loginViewModel;
	private static final long serialVersionUID = 1L;
	private JTextField usernameInputField;
	private JTextField passwordInputField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	private LoginController loginController;

	/**
	 * Create the panel.
	 */
	public LoginView(LoginViewModel loginViewModel, LoginController controller) {
		this.setLayout(null);

		this.loginController = controller;
		this.loginViewModel = loginViewModel;
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(347, 21, 188, 49);
		add(lblNewLabel);
		
		usernameInputField = new JTextField();
		usernameInputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				LoginState currentState = loginViewModel.getState();
				currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
				loginViewModel.setState(currentState);
			}
		});
		usernameInputField.setBounds(57, 160, 549, 49);
		add(usernameInputField);
		usernameInputField.setColumns(10);
		
		passwordInputField = new JTextField();
		passwordInputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				LoginState currentState = loginViewModel.getState();
				currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
				loginViewModel.setState(currentState);
			}
		});
		passwordInputField.setColumns(10);
		passwordInputField.setBounds(57, 316, 549, 49);
		add(passwordInputField);
		
		lblNewLabel_1 = new JLabel("Enter your password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(57, 275, 303, 25);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Enter your username:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(57, 123, 303, 25);
		add(lblNewLabel_2);
		
		JButton submit = new JButton("Log In");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginState currentState = loginViewModel.getState();

				loginController.execute(
						currentState.getUsername(),
						currentState.getPassword()
				);
			}
		});
		submit.setBounds(360, 422, 136, 57);
		add(submit);
	}

	public void actionPerformed(ActionEvent evt) {}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LoginState state = (LoginState) evt.getNewValue();
		if (state.getLoginError() != null) {
			JOptionPane.showMessageDialog(this, state.getLoginError());
		}
	}
}
