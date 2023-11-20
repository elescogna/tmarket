package view;

import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderState;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.go_home.GoHomeController;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateOrderView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "create order";
    private static final long serialVersionUID = 1L;
    private JTextField otherAddress;
    private JTextField buyerEmail;
    private CreateOrderController createOrderController;
    private CreateOrderViewModel createOrderViewModel;
    private JButton create;
    private GoHomeController goHomeController;

    /**
     * Create the panel.
     */
    public CreateOrderView(CreateOrderController createOrderController, CreateOrderViewModel createOrderViewModel, GoHomeController goHomeController) {
        this.setLayout(null);

        this.createOrderController = createOrderController;
        this.createOrderViewModel = createOrderViewModel;
        this.goHomeController = goHomeController;

        JLabel lblNewLabel = new JLabel("Create New Order");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(315, 11, 268, 58);
        add(lblNewLabel);

        otherAddress = new JTextField();
        otherAddress.setFont(new Font("Tahoma", Font.PLAIN, 10));
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
        otherAddress.setBounds(30, 428, 497, 35);
        add(otherAddress);

        JLabel lblNewLabel_1 = new JLabel("Enter the buyer's e-mail:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(30, 175, 219, 25);
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Would you like to have the order's pickup location to be the same as the item's pickup address?");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(30, 270, 858, 25);
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
        sameAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        sameAddress.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
        sameAddress.setEditable(true);
        sameAddress.setBounds(30, 320, 149, 35);
        add(sameAddress);

        JLabel lblNewLabel_1_2 = new JLabel("If not, please specify the order's pickup location.");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(31, 380, 455, 25);
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
        buyerEmail.setBounds(30, 224, 497, 35);
        add(buyerEmail);
        
        JLabel lblNewLabel_1_3 = new JLabel("Enter your e-mail:");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(30, 73, 219, 25);
        add(lblNewLabel_1_3);
        
        create = new JButton("Create New Order");
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(create)) {
                    CreateOrderState currentState = createOrderViewModel.getState();

                    createOrderController.execute(
                            currentState.getItem(),
                            currentState.getStudent(),
                            currentState.getBuyerEmail(),
                            currentState.getSameAddress(),
                            currentState.getOtherAddress()
                    );
                }
            }
        });
        create.setFont(new Font("Tahoma", Font.PLAIN, 20));
        create.setBounds(30, 490, 255, 65);
        add(create);
        
        JButton btnBackToHome = new JButton("Back To Home");
        btnBackToHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                goHomeController.execute();
        	}
        });
        btnBackToHome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnBackToHome.setBounds(600, 490, 255, 65);
        add(btnBackToHome);
    }

    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateOrderState state = (CreateOrderState) evt.getNewValue();
        if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
        }
    }
}
