package view;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ViewItemView extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public ViewItemView() {
        this.setLayout(null);

        JLabel lblTitle = new JLabel("View Item");
        lblTitle.setBounds(236, 12, 133, 26);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 25));
        add(lblTitle);

        JLabel lblPhoto = new JLabel("Photo");
        lblPhoto.setBounds(12, 211, 205, 17);
        add(lblPhoto);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(236, 66, 83, 17);
        lblName.setFont(new Font("Modern No. 20", Font.BOLD, 15));
        add(lblName);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Dialog", Font.BOLD, 15));
        lblDescription.setBounds(236, 95, 96, 17);
        add(lblDescription);

        JLabel lblCondition = new JLabel("Condition:");
        lblCondition.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCondition.setBounds(236, 124, 83, 17);
        add(lblCondition);

        JLabel lblSoldYet = new JLabel("Sold Yet:");
        lblSoldYet.setFont(new Font("Dialog", Font.BOLD, 15));
        lblSoldYet.setBounds(236, 211, 75, 17);
        add(lblSoldYet);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 15));
        lblAge.setBounds(236, 182, 50, 17);
        add(lblAge);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPrice.setBounds(236, 153, 50, 17);
        add(lblPrice);

        JLabel lblPostedAt = new JLabel("Posted at:");
        lblPostedAt.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPostedAt.setBounds(236, 298, 83, 17);
        add(lblPostedAt);

        JLabel lblType = new JLabel("Type:");
        lblType.setFont(new Font("Dialog", Font.BOLD, 15));
        lblType.setBounds(236, 269, 41, 17);
        add(lblType);

        JLabel lblOwnerName = new JLabel("Owner Name:");
        lblOwnerName.setFont(new Font("Dialog", Font.BOLD, 15));
        lblOwnerName.setBounds(236, 240, 113, 17);
        add(lblOwnerName);

        JLabel lblCustom3 = new JLabel("Custom 3");
        lblCustom3.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom3.setBounds(236, 385, 83, 17);
        add(lblCustom3);

        JLabel lblCustom2 = new JLabel("Custom 2");
        lblCustom2.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom2.setBounds(236, 356, 83, 17);
        add(lblCustom2);

        JLabel lblCustom1 = new JLabel("Custom 1");
        lblCustom1.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom1.setBounds(236, 327, 83, 17);
        add(lblCustom1);

        JLabel lblCustom4 = new JLabel("Custom 4");
        lblCustom4.setFont(new Font("Dialog", Font.BOLD, 15));
        lblCustom4.setBounds(236, 414, 83, 17);
        add(lblCustom4);
        
        JButton btnBack = new JButton("Done");
        btnBack.setBounds(510, 418, 83, 27);
        add(btnBack);
    }
}
