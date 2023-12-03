package view;

import entities.Order;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.view_order.ViewOrderState;
import interface_adapter.view_order.ViewOrderViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ViewOrderView extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private JLabel lblTitle;
    private JLabel lblPhoto;
    private JLabel lblItemName;
    private JLabel lblBuyerEmail;
    private JLabel lblSellerEmail;
    private JLabel lblPickupAddress;
    private JButton btnBack;

    private ViewOrderViewModel viewOrderViewModel;
    private GoHomeController goHomeController;
    private JList<String> listDirections;
    private Image backgroundImage;

    /**
     * Create the panel.
     */
    public ViewOrderView(ViewOrderViewModel viewOrderViewModel,
            GoHomeController goHomeController) {
    	setBackground(new Color(0, 0, 0));
        this.viewOrderViewModel = viewOrderViewModel;
        this.goHomeController = goHomeController;

        this.viewOrderViewModel.addPropertyChangeListener(this);

        this.setLayout(null);
        this.setSize(new Dimension(650, 482));
        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        lblTitle = new JLabel("View Order");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setBounds(255, 28, 140, 26);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 26));
        add(lblTitle);

        lblPhoto = new JLabel("Photo");
        lblPhoto.setForeground(new Color(255, 255, 255));
        lblPhoto.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblPhoto.setBounds(12, 211, 205, 17);
        add(lblPhoto);

        lblItemName = new JLabel("Item Name: ");
        lblItemName.setForeground(new Color(255, 255, 255));
        lblItemName.setBounds(256, 90, 380, 17);
        lblItemName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        add(lblItemName);

        lblBuyerEmail = new JLabel("Buyer Email: ");
        lblBuyerEmail.setForeground(new Color(255, 255, 255));
        lblBuyerEmail.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblBuyerEmail.setBounds(256, 119, 380, 17);
        add(lblBuyerEmail);

        lblSellerEmail = new JLabel("Seller Email: ");
        lblSellerEmail.setForeground(new Color(255, 255, 255));
        lblSellerEmail.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblSellerEmail.setBounds(256, 148, 380, 17);
        add(lblSellerEmail);

        lblPickupAddress = new JLabel("Pickup Address: ");
        lblPickupAddress.setForeground(new Color(255, 255, 255));
        lblPickupAddress.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblPickupAddress.setBounds(256, 177, 380, 17);
        add(lblPickupAddress);

        btnBack = new JButton("Done");

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewOrderView.this.goHomeController.execute();
            }
        });

        btnBack.setBounds(553, 443, 83, 27);
        add(btnBack);

        JLabel lblDirections = new JLabel("Directions:");
        lblDirections.setForeground(new Color(255, 255, 255));
        lblDirections.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblDirections.setBounds(255, 211, 380, 17);
        add(lblDirections);

        listDirections = new JList<String>();
        JScrollPane directionsScrollPane = new JScrollPane(listDirections);
        directionsScrollPane.setBounds(255, 240, 381, 191);
        add(directionsScrollPane);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // someone outside of this will have updated the state and caused this
        // property listener to fire
        ViewOrderState viewOrderState = (ViewOrderState)evt.getNewValue();

        String error = viewOrderState.getCurrentOrderError();

        if (error != null) {
            JOptionPane.showMessageDialog(ViewOrderView.this, error);
            viewOrderState.setCurrentOrderError(
                    null); // the error has been displayed so we can get rid of it
        } else {     // display everything as normal
            Order currentOrder = viewOrderState.getCurrentOrder();
            String currentItemNameToShow = viewOrderState.getCurrentItemNameToShow();
            ArrayList<String> currentDirections =
                viewOrderState.getCurrentDirections();

            lblItemName.setText("Item Name: " + currentItemNameToShow);
            lblBuyerEmail.setText("Buyer Email: " + currentOrder.getBuyerEmail());
            lblSellerEmail.setText("Seller Email: " + currentOrder.getSellerEmail());
            lblPickupAddress.setText("Pickup Address: " +
                    currentOrder.getPickupLocation());

            DefaultListModel<String> listDirectionsModel =
                new DefaultListModel<String>();

            for (String direction : currentDirections) {
                listDirectionsModel.addElement(direction);
            }

            this.listDirections.setModel(listDirectionsModel);
        }
    }
}
