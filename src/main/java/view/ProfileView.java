package view;

import entities.Item;
import entities.Order;
import entities.Student;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.home.HomeState;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ProfileView extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private GoHomeController goHomeController;
	private ProfileViewModel profileViewModel;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JList<String> listPostedItems;
	private JList<String> listOrders;

	public ProfileView(ProfileController profileController, ProfileViewModel
			profileViewModel, GoHomeController goHomeController) {
		this.setLayout(null);
		setBackground(new Color(214, 186, 250));

		listPostedItems = new JList<String>();
		listOrders = new JList<String>();
		this.profileViewModel = profileViewModel;
		this.profileViewModel.addPropertyChangeListener(this);
		this.goHomeController = goHomeController;

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
				goHomeController.execute();
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

					// TODO: Implement View Order
					// String itemId = homeState.getAllPosts().get(index).getId();
					// Student currentStudent = homeState.getStudent();

					// HomeView.this.viewItemController.execute(itemId, currentStudent);
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
