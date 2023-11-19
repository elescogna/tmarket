package view;

import entities.Item;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class HomeView extends JPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private JButton btn;
    private JButton btnPost;
    private JButton btnProfile;
    private JButton btnSearch;
    private JLabel lblTitle;
    private JScrollPane scrollPaneItemsScrollPane;
    private JList<String> listItems;
    private HomeController homeController;
    private HomeViewModel homeViewModel;

    /**
     * Create the panel.
     */
    public HomeView(HomeViewModel homeViewModel, HomeController homeController) {
        this.setLayout(null);

        this.homeController = homeController;
        this.homeViewModel = homeViewModel;

        btn = new JButton("New button");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        add(btn);

        btnPost = new JButton("Post");
        btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        btnPost.setBounds(316, 97, 124, 47);
        add(btnPost);

        btnProfile = new JButton("Profile");
        btnProfile.setBounds(316, 213, 124, 47);
        add(btnProfile);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(316, 155, 124, 47);
        add(btnSearch);

        lblTitle = new JLabel("TmarkeT");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 33));
        lblTitle.setBounds(10, 22, 430, 37);
        add(lblTitle);

        listItems = new JList<String>();

        scrollPaneItemsScrollPane = new JScrollPane(listItems);
        scrollPaneItemsScrollPane.setBounds(21, 70, 285, 219);
        add(scrollPaneItemsScrollPane);

        // updating the list right away
        homeController.execute();
        this.updateItemsList();

        // TODO: add the the code that actually makes the view work (using the
        // contructor parameters)
    }

    public void updateItemsList() {
        ArrayList<Item> items = this.homeViewModel.getState().getWantedPosts();

        DefaultListModel<String> listItemsModel = new DefaultListModel<String>();

        for (Item item : items) {
            listItemsModel.addElement(String.format("%s %d", item.getName(),
                        String.valueOf(item.getPrice())));
        }

        this.listItems.setModel(listItemsModel);
        this.listItems.setSelectedIndex(0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateItemsList();
    }
}
