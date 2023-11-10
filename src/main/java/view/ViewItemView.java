package view;

import entities.Clothing;
import entities.Furniture;
import entities.Item;
import entities.SchoolItem;
import entities.Technology;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemState;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    private HomeController homeController;

    /**
     * Create the panel.
     */
    public ViewItemView(HomeController homeController) {
        this.homeController = homeController;

        this.setLayout(null);

        lblTitle = new JLabel("View Item");
        lblTitle.setBounds(236, 12, 133, 26);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 25));
        add(lblTitle);

        lblPhoto = new JLabel("Photo");
        lblPhoto.setBounds(12, 211, 205, 17);
        add(lblPhoto);

        lblName = new JLabel("Name:");
        lblName.setBounds(236, 66, 83, 17);
        lblName.setFont(new Font("Modern No. 20", Font.BOLD, 15));
        add(lblName);

        lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Dialog", Font.BOLD, 15));
        lblDescription.setBounds(236, 95, 96, 17);
        add(lblDescription);

        lblCondition = new JLabel("Condition:");
        lblCondition.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCondition.setBounds(236, 124, 83, 17);
        add(lblCondition);

        lblSoldYet = new JLabel("Sold Yet:");
        lblSoldYet.setFont(new Font("Dialog", Font.BOLD, 15));
        lblSoldYet.setBounds(236, 211, 75, 17);
        add(lblSoldYet);

        lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 15));
        lblAge.setBounds(236, 182, 50, 17);
        add(lblAge);

        lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPrice.setBounds(236, 153, 50, 17);
        add(lblPrice);

        lblPostedAt = new JLabel("Posted at:");
        lblPostedAt.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPostedAt.setBounds(236, 298, 83, 17);
        add(lblPostedAt);

        lblType = new JLabel("Type:");
        lblType.setFont(new Font("Dialog", Font.BOLD, 15));
        lblType.setBounds(236, 269, 41, 17);
        add(lblType);

        lblOwnerName = new JLabel("Owner Name:");
        lblOwnerName.setFont(new Font("Dialog", Font.BOLD, 15));
        lblOwnerName.setBounds(236, 240, 113, 17);
        add(lblOwnerName);

        lblCustom3 = new JLabel("Custom 3");
        lblCustom3.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom3.setBounds(236, 385, 83, 17);
        add(lblCustom3);

        lblCustom2 = new JLabel("Custom 2");
        lblCustom2.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom2.setBounds(236, 356, 83, 17);
        add(lblCustom2);

        lblCustom1 = new JLabel("Custom 1");
        lblCustom1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom1.setBounds(236, 327, 83, 17);
        add(lblCustom1);

        lblCustom4 = new JLabel("Custom 4");
        lblCustom4.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom4.setBounds(236, 414, 83, 17);
        add(lblCustom4);

        btnBack = new JButton("Done");

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { homeController.execute(); }
        });

        btnBack.setBounds(510, 418, 83, 27);
        add(btnBack);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewItemState viewItemState = (ViewItemState)evt.getNewValue();

        String error = viewItemState.getCurrentItemError();

        if (error != null) {
            JOptionPane.showMessageDialog(ViewItemView.this, error);
            viewItemState.setCurrentItemError(null); // the error has been displayed
        } else {                                   // display everything as normal
            Item currentItem = viewItemState.getCurrentItem();

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

            if (currentItem instanceof Clothing) {
                lblCustom1.setText("Brand: " + ((Clothing)currentItem).getBrand());
                lblCustom2.setText("Colour: " + ((Clothing)currentItem).getColour());
                lblCustom3.setText("Size: " + ((Clothing)currentItem).getSize());
                lblCustom3.setText("Material: " +
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
                lblCustom2.setText("Height: " + ((Furniture)currentItem).getHeight());

                lblCustom1.setVisible(true);
                lblCustom2.setVisible(true);
                lblCustom3.setVisible(true);
                lblCustom4.setVisible(false);
            }
        }
    }
}
