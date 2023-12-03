package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
	public final String viewName = "log in";
	private final LoginViewModel loginViewModel;
	private static final long serialVersionUID = 1L;
	private JTextField usernameInputField;
	private JTextField passwordInputField;
	private JLabel pwdLabel;
	private JLabel userNameLabel;
	private LoginController loginController;
	private Image backgroundImage;

	/**
	 * Create the panel.
	 */
	public LoginView(LoginViewModel loginViewModel, LoginController controller) {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		this.setLayout(null);

		loginViewModel.addPropertyChangeListener(this);

		this.loginController = controller;
		this.loginViewModel = loginViewModel;

		try {
			String imagePath = "C:\\Users\\Aina\\IdeaProjects\\csc207-project\\assets\\background_image.png";
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 26));
		lblNewLabel.setBounds(431, 241, 138, 49);
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
		usernameInputField.setBounds(552, 321, 207, 20);
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
		passwordInputField.setBounds(552, 392, 207, 20);
		add(passwordInputField);

		pwdLabel = new JLabel("Enter your password:");
		pwdLabel.setForeground(new Color(255, 255, 255));
		pwdLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		pwdLabel.setBounds(286, 388, 263, 25);
		add(pwdLabel);

		userNameLabel = new JLabel("Enter your username:");
		userNameLabel.setForeground(new Color(255, 255, 255));
		userNameLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		userNameLabel.setBounds(286, 317, 256, 25);
		add(userNameLabel);

		JButton submit = new JButton("Log In");
		submit.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginState currentState = loginViewModel.getState();

				loginController.execute(
						currentState.getUsername(),
						currentState.getPassword()
				);
			}
		});
		submit.setBounds(452, 460, 96, 20);
		add(submit);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
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
