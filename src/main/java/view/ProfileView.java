package view;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

public class ProfileView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProfileView(ProfileController profileController, ProfileViewModel
					   profileViewModel) {
		this.setLayout(null);
		setBackground(new Color(214, 186, 250));
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(48, 71, 49, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Uoft Email: ");
		lblNewLabel_1.setBounds(48, 106, 74, 14);
		add(lblNewLabel_1);

	}

}
