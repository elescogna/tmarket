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
	private JLabel imgLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	private LoginController loginController;
	private Image backgroundImage;

	/**
	 * Create the panel.
	 */
	public LoginView(LoginViewModel loginViewModel, LoginController controller) {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		this.setLayout(null);

		this.loginController = controller;
		this.loginViewModel = loginViewModel;

		try {
			String imagePath = "C:\\Users\\Aina\\IdeaProjects\\csc207-project\\assets\\trial.png";
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();  // Handle the exception according to your needs
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

		lblNewLabel_1 = new JLabel("Enter your password:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_1.setBounds(286, 388, 263, 25);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Enter your username:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel_2.setBounds(286, 317, 256, 25);
		add(lblNewLabel_2);

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
