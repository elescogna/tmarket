package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderState;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.home.HomeViewModel;

public class CreateOrderView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "create order";
    private static final long serialVersionUID = 1L;
    private JTextField otherAddress;
    private JTextField buyerEmail;
    private CreateOrderController createOrderController;
    private CreateOrderViewModel createOrderViewModel;
    private JButton create;
    private HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;
    private Image backgroundImage;
    private JLabel lblItemName;

    /**
     * Create the panel.
     */
    public CreateOrderView(CreateOrderController createOrderController, CreateOrderViewModel createOrderViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
    	setBackground(new Color(0, 0, 0));
        this.setLayout(null);

        this.createOrderController = createOrderController;
        this.createOrderViewModel = createOrderViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;

        this.createOrderViewModel.addPropertyChangeListener(this);

        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel lblNewLabel = new JLabel("Create New Order");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 26));
        lblNewLabel.setBounds(358, 69, 268, 58);
        add(lblNewLabel);

        otherAddress = new JTextField();
        otherAddress.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 10));
        otherAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateOrderState currentState = createOrderViewModel.getState();
                String text = otherAddress.getText() + e.getKeyChar();
                currentState.setOtherAddress(text);
                createOrderViewModel.setState(currentState);
            }
        });
        otherAddress.setColumns(10);
        otherAddress.setBounds(86, 586, 821, 35);
        add(otherAddress);

        JLabel lblNewLabel_1 = new JLabel("Enter the buyer's e-mail:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1.setBounds(86, 280, 219, 25);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(
                "Would you like to have the order's pickup location to be the same as the item's pickup address?");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(86, 409, 858, 25);
        add(lblNewLabel_1_1);

        JComboBox sameAddress = new JComboBox();
        sameAddress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateOrderState currentState = createOrderViewModel.getState();
                String answer = (String)sameAddress.getSelectedItem();
                currentState.setSameAddress(answer);
                createOrderViewModel.setState(currentState);
            }
        });
        sameAddress.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 20));
        sameAddress.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        sameAddress.setEditable(true);
        sameAddress.setBounds(86, 474, 213, 35);
        add(sameAddress);

        JLabel lblNewLabel_1_2 =
            new JLabel("If not, please specify the order's pickup location.");
        lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblNewLabel_1_2.setBounds(86, 537, 455, 25);
        add(lblNewLabel_1_2);

        buyerEmail = new JTextField();
        buyerEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateOrderState currentState = createOrderViewModel.getState();
                String text = buyerEmail.getText() + e.getKeyChar();
                currentState.setBuyerEmail(text);
                createOrderViewModel.setState(currentState);
            }
        });
        buyerEmail.setColumns(10);
        buyerEmail.setBounds(86, 340, 821, 35);
        add(buyerEmail);

        lblItemName = new JLabel("Item Name: ");
        lblItemName.setForeground(new Color(255, 255, 255));
        lblItemName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblItemName.setBounds(86, 222, 219, 25);
        add(lblItemName);

        create = new JButton("Create New Order");
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(create)) {
                    CreateOrderState currentState = createOrderViewModel.getState();

                    CreateOrderView.this.createOrderController.execute(
                            currentState.getItem(), currentState.getStudent(),
                            currentState.getBuyerEmail(), currentState.getSameAddress(),
                            currentState.getOtherAddress());
                }
            }
        });

        create.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 20));
        create.setBounds(119, 698, 219, 42);
        add(create);

        JButton btnBackToHome = new JButton("Back To Home");
        btnBackToHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                CreateOrderView.this.viewManagerModel.setActiveView(CreateOrderView.this.homeViewModel.getViewName());
                CreateOrderView.this.viewManagerModel.firePropertyChanged();
        	}
        });
        btnBackToHome.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 20));
        btnBackToHome.setBounds(610, 698, 236, 43);
        add(btnBackToHome);
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
        CreateOrderState state = (CreateOrderState)evt.getNewValue();

        if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
            state.setEmailError(null);
        } else {
            this.lblItemName.setText("Item Name: " + state.getItem().getName());
        }
    }
}
