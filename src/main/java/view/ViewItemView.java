package view;

import entities.Clothing;
import entities.Furniture;
import entities.Item;
import entities.SchoolItem;
import entities.Student;
import entities.Technology;
import interface_adapter.contacting.ContactingController;
import interface_adapter.go_create_order.GoCreateOrderController;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemState;
import interface_adapter.view_item.ViewItemViewModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewItemView extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private JLabel lblTitle;
    private JLabel lblPhoto;
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblCondition;
    private JLabel lblSoldYet;
    private JLabel lblAge;
    private JLabel lblPrice;
    private JLabel lblPostedAt;
    private JLabel lblType;
    private JLabel lblOwnerName;
    private JLabel lblCustom3;
    private JLabel lblCustom2;
    private JLabel lblCustom1;
    private JLabel lblCustom4;
    private JButton btnBack;
    private JButton btnContactSeller;
    private JButton btnFulfillOrder;

    private ViewItemViewModel viewItemViewModel;
    private GoHomeController goHomeController;
    private ContactingController contactingController;
    private GoCreateOrderController goCreateOrderController;
    private Image backgroundImage;

    /**
     * Create the panel.
     */
    public ViewItemView(ViewItemViewModel viewItemViewModel,
            GoHomeController goHomeController,
            ContactingController contactingController,
            GoCreateOrderController goCreateOrderController) {
        setBackground(new Color(0, 0, 0));
        this.viewItemViewModel = viewItemViewModel;
        this.goHomeController = goHomeController;
        this.contactingController = contactingController;
        this.goCreateOrderController = goCreateOrderController;

        this.viewItemViewModel.addPropertyChangeListener(this);

        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(null);
        this.setSize(new Dimension(1004, 802));

        lblTitle = new JLabel("View Item");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setBounds(434, 101, 133, 26);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 26));
        add(lblTitle);

        lblPhoto = new JLabel("Image:");
        lblPhoto.setForeground(new Color(255, 255, 255));
        lblPhoto.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblPhoto.setBounds(76, 334, 399, 316);
        add(lblPhoto);

        lblName = new JLabel("Name:");
        lblName.setForeground(new Color(255, 255, 255));
        lblName.setBounds(76, 199, 380, 17);
        lblName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        add(lblName);

        lblDescription = new JLabel("Description:");
        lblDescription.setForeground(new Color(255, 255, 255));
        lblDescription.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblDescription.setBounds(76, 246, 380, 17);
        add(lblDescription);

        lblCondition = new JLabel("Condition:");
        lblCondition.setForeground(new Color(255, 255, 255));
        lblCondition.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblCondition.setBounds(76, 293, 380, 17);
        add(lblCondition);

        lblSoldYet = new JLabel("Sold Yet:");
        lblSoldYet.setForeground(new Color(255, 255, 255));
        lblSoldYet.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblSoldYet.setBounds(547, 293, 380, 17);
        add(lblSoldYet);

        lblAge = new JLabel("Age:");
        lblAge.setForeground(new Color(255, 255, 255));
        lblAge.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblAge.setBounds(547, 246, 380, 17);
        add(lblAge);

        lblPrice = new JLabel("Price:");
        lblPrice.setForeground(new Color(255, 255, 255));
        lblPrice.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblPrice.setBounds(547, 199, 380, 17);
        add(lblPrice);

        lblPostedAt = new JLabel("Posted at:");
        lblPostedAt.setForeground(new Color(255, 255, 255));
        lblPostedAt.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblPostedAt.setBounds(547, 430, 380, 17);
        add(lblPostedAt);

        lblType = new JLabel("Type:");
        lblType.setForeground(new Color(255, 255, 255));
        lblType.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblType.setBounds(547, 383, 380, 17);
        add(lblType);

        lblOwnerName = new JLabel("Owner Name:");
        lblOwnerName.setForeground(new Color(255, 255, 255));
        lblOwnerName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblOwnerName.setBounds(547, 334, 380, 17);
        add(lblOwnerName);

        lblCustom3 = new JLabel("Custom 3");
        lblCustom3.setForeground(new Color(255, 255, 255));
        lblCustom3.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblCustom3.setBounds(547, 564, 380, 17);
        add(lblCustom3);

        lblCustom2 = new JLabel("Custom 2");
        lblCustom2.setForeground(new Color(255, 255, 255));
        lblCustom2.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblCustom2.setBounds(547, 521, 380, 17);
        add(lblCustom2);

        lblCustom1 = new JLabel("Custom 1");
        lblCustom1.setForeground(new Color(255, 255, 255));
        lblCustom1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblCustom1.setBounds(547, 480, 380, 17);
        add(lblCustom1);

        lblCustom4 = new JLabel("Custom 4");
        lblCustom4.setForeground(new Color(255, 255, 255));
        lblCustom4.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        lblCustom4.setBounds(547, 609, 380, 17);
        add(lblCustom4);

        btnBack = new JButton("Done");

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewItemView.this.goHomeController.execute();
            }
        });

        btnBack.setBounds(691, 710, 83, 27);
        add(btnBack);

        btnContactSeller = new JButton("Contact Seller");
        btnContactSeller.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewItemState currentState =
                    ViewItemView.this.viewItemViewModel.getState();

                ViewItemView.this.contactingController.execute(
                        currentState.getCurrentItem());
            }
        });
        btnContactSeller.setBounds(165, 710, 123, 27);
        add(btnContactSeller);

        btnFulfillOrder = new JButton("Fulfill Order");
        btnFulfillOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewItemState state = viewItemViewModel.getState();
                Student currentStudent = state.getCurrentStudent();
                Item item = state.getCurrentItem();
                ViewItemView.this.goCreateOrderController.execute(currentStudent, item);
            }
        });
        btnFulfillOrder.setBounds(428, 710, 123, 27);
        add(btnFulfillOrder);
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
        ViewItemState viewItemState = (ViewItemState)evt.getNewValue();

        String error = viewItemState.getCurrentItemError();

        if (error != null) {
            JOptionPane.showMessageDialog(ViewItemView.this, error);
            viewItemState.setCurrentItemError(
                    null); // the error has been displayed so we can get rid of it
        } else {     // display everything as normal
            Item currentItem = viewItemState.getCurrentItem();
            Student currentStudent = viewItemState.getCurrentStudent();

            boolean currentStudentIsOwner =
                currentStudent.getId().equals(currentItem.getOwnerId());

            this.btnContactSeller.setVisible(!currentStudentIsOwner);
            this.btnFulfillOrder.setVisible(currentStudentIsOwner);

            this.lblName.setText("Name: " + currentItem.getName());
            this.lblDescription.setText("Description: " +
                    currentItem.getDescription());
            this.lblCondition.setText("Condition: " + currentItem.getCondition());
            this.lblSoldYet.setText("Sold Yet: " +
                    String.valueOf(currentItem.isSoldYet()));
            this.lblAge.setText(String.valueOf("Age: " + currentItem.getAge()));
            this.lblPrice.setText(String.valueOf("Price: " + currentItem.getPrice()));
            this.lblType.setText("Type: " + currentItem.getType());
            this.lblPostedAt.setText("Posted At: " +
                    currentItem.getCreationTime().toString());
            this.lblPhoto.setIcon(viewItemState.getCurrentItemImage());

            if (currentItem instanceof Clothing) {
                lblCustom1.setText("Brand: " + ((Clothing)currentItem).getBrand());
                lblCustom2.setText("Colour: " + ((Clothing)currentItem).getColour());
                lblCustom3.setText("Size: " + ((Clothing)currentItem).getSize());
                lblCustom4.setText("Material: " +
                        ((Clothing)currentItem).getMaterial());

                lblCustom1.setVisible(true);
                lblCustom2.setVisible(true);
                lblCustom3.setVisible(true);
                lblCustom4.setVisible(true);
            } else if (currentItem instanceof Technology) {
                lblCustom1.setText("Brand: " + ((Technology)currentItem).getBrand());
                lblCustom2.setText("Colour: " + ((Technology)currentItem).getColour());
                lblCustom3.setText("Capabilities: " + ((Technology)currentItem)
                        .getCapabilities()
                        .toString()
                        .replace("[", "")
                        .replace("]", ""));

                lblCustom1.setVisible(true);
                lblCustom2.setVisible(true);
                lblCustom3.setVisible(true);
                lblCustom4.setVisible(false);
            } else if (currentItem instanceof SchoolItem) {
                lblCustom1.setText("Brand: " + ((SchoolItem)currentItem).getBrand());
                lblCustom2.setText("Colour: " + ((SchoolItem)currentItem).getColour());

                lblCustom1.setVisible(true);
                lblCustom2.setVisible(true);
                lblCustom3.setVisible(false);
                lblCustom4.setVisible(false);
            } else { // currentItem instanceof Furniture
                lblCustom1.setText("Length: " + ((Furniture)currentItem).getLength());
                lblCustom2.setText("Width: " + ((Furniture)currentItem).getWidth());
                lblCustom3.setText("Height: " + ((Furniture)currentItem).getHeight());

                lblCustom1.setVisible(true);
                lblCustom2.setVisible(true);
                lblCustom3.setVisible(true);
                lblCustom4.setVisible(false);
            }
        }
    }
}
