package view;

import entities.Item;
import entities.Order;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.view_order.ViewOrderController;
import interface_adapter.view_order.ViewOrderViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileView extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private GoHomeController goHomeController;
	private ProfileViewModel profileViewModel;
	private ViewOrderController viewOrderController;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JList<String> listPostedItems;
	private JList<String> listOrders;
	private Image backgroundImage;

    public ProfileView(ProfileController profileController, ProfileViewModel
            profileViewModel, GoHomeController goHomeController,
            ViewOrderController viewOrderController) {
		this.setLayout(null);
		setBackground(new Color(214, 186, 250));

		listPostedItems = new JList<String>();
		listOrders = new JList<String>();
		this.profileViewModel = profileViewModel;
		this.profileViewModel.addPropertyChangeListener(this);
		this.goHomeController = goHomeController;
		this.viewOrderController = viewOrderController;

		try {
			String imagePath = "C:\\Users\\Aina\\IdeaProjects\\csc207-project\\assets\\background_image.png";
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel nameDisplayLabel = new JLabel("Name: ");
		nameDisplayLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameDisplayLabel.setBounds(576, 97, 216, 24);
		add(nameDisplayLabel);

		JLabel emailDisplayLabel = new JLabel("UofT Email: ");
		emailDisplayLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailDisplayLabel.setBounds(576, 233, 242, 41);
		add(emailDisplayLabel);

		JLabel lblUserProfile = new JLabel("My Profile");
		lblUserProfile.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUserProfile.setBounds(339, 11, 166, 41);
		add(lblUserProfile);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileView.this.goHomeController.execute();
			}
		});
		btnNewButton.setBounds(669, 461, 123, 48);
		add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane(listOrders);
		scrollPane.setBounds(36, 86, 241, 423);
		add(scrollPane);

		JLabel lblOrders = new JLabel("My Posted Items:");
		lblOrders.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrders.setBounds(339, 46, 166, 41);
		add(lblOrders);

		JScrollPane scrollPane_1 = new JScrollPane(listPostedItems);
		scrollPane_1.setBounds(306, 88, 239, 421);
		add(scrollPane_1);

		JLabel lblOrders_1 = new JLabel("My Orders:");
		lblOrders_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrders_1.setBounds(88, 46, 117, 41);
		add(lblOrders_1);

		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		nameLabel.setBounds(576, 151, 216, 61);
		add(nameLabel);

		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		emailLabel.setBounds(576, 299, 216, 61);
		add(emailLabel);

		listOrders.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
                    int index = listOrders.locationToIndex(e.getPoint());

                    ProfileState profileState = ProfileView.this.profileViewModel.getState();

                    String orderId = profileState.getOrders().get(index).getId();
                    String currentStudentEmail = profileState.getUoftEmail();

                    ProfileView.this.viewOrderController.execute(orderId, currentStudentEmail);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public void updateLists() {
		ArrayList<Order> orders = this.profileViewModel.getState().getOrders();
		ArrayList<Item> items = this.profileViewModel.getState().getPostedItems();

		DefaultListModel<String> orderModel = new DefaultListModel<String>();
		DefaultListModel<String> itemModel = new DefaultListModel<String>();

		for (Order order : orders) {
			orderModel.addElement(
					String.format("%s", order.getItemName()));
		}

		for (Item item : items) {
			itemModel.addElement(
					String.format("%s %s", item.getName(), item.getPrice()));
		}

		this.listOrders.setModel(orderModel);
		this.listPostedItems.setModel(itemModel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ProfileState currentState = this.profileViewModel.getState();
		nameLabel.setText(currentState.getName());
		emailLabel.setText(currentState.getUoftEmail());
		this.updateLists();
	}
}
