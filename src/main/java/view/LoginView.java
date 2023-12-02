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
import java.net.URL;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
//	private JPanel map;
//	private JPanel home;
//	private JFrame app;
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

//		map = new JPanel();
//		map.setPreferredSize(new Dimension(1200, 800));
//		JLabel imgLabel = new JLabel(new ImageIcon("assets/trial.png"));
//		imgLabel.setPreferredSize(new Dimension(1200, 500));
//		map.add(imgLabel);
//
//		app = new JFrame();
//
//		home = new JPanel();
//		home.setLayout(new FlowLayout());
//		home.add(map);
//		app.add(home);

//		JLabel imgLabel = new JLabel(new ImageIcon("C:\\Users\\Aina\\IdeaProjects\\csc207-project\\assets\\trial_1.png"));
//		imgLabel.setPreferredSize(new Dimension(1200, 500));
//		System.out.println(imgLabel);
//		add(imgLabel);
//
//		String currentDirectory = System.getProperty("user.dir");
//
//		// Print the current directory
//		System.out.println("Current Directory: " + currentDirectory);
//
//		JLabel imgLabel = new JLabel(new ImageIcon("src/java/pleasework.png"));
//		imgLabel.setPreferredSize(new Dimension(1200, 500));
//		System.out.println(imgLabel);
//		add(imgLabel);
////		try {
////			String imagePath = "/Users/apank/IdeaProjects/csc207-project/src/main/java/trial_1.png";
////			backgroundImage = ImageIO.read(new File(imagePath));
////		} catch (IOException e) {
////			// Print the exception details to the console for debugging
////			System.err.println("Error loading image: " + e.getMessage());
////			e.printStackTrace();
////		}
//
//		try {
//			URL imageURL = getClass().getResource("src/main/java/pleasework.png");
//			System.out.println("Image URL: " + imageURL);
//			ImageIcon imageIcon = new ImageIcon(imageURL);
//
//			Image image = imageIcon.getImage();
//			backgroundImage = image;
//		} catch (Exception e) {
//			System.out.println("Oh fuck");
//			e.printStackTrace();
//		}

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

	public void actionPerformed(ActionEvent evt) {}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LoginState state = (LoginState) evt.getNewValue();
		if (state.getLoginError() != null) {
			JOptionPane.showMessageDialog(this, state.getLoginError());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}
}
