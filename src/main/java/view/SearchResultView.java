package view;

import entities.Item;
import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.view_item.ViewItemController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SearchResultView extends JPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private final JList list;
    private JScrollPane listScrollPane;
    private JButton homeButton;
    private HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;
    private SearchResultViewModel searchResultViewModel;
    private ViewItemController viewItemController;
    private JLabel lblNewLabel;
    private Image backgroundImage;

    /**
     * Create the panel.
     */
    public SearchResultView(HomeViewModel homeViewModel,
            SearchResultViewModel searchResultViewModel,
            ViewItemController viewItemController, ViewManagerModel viewManagerModel) {
    	setBackground(new Color(0, 0, 0));
        this.setLayout(null);
        this.searchResultViewModel = searchResultViewModel;
        this.viewItemController = viewItemController;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchResultViewModel.addPropertyChangeListener(this);

        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        list = new JList();

        listScrollPane = new JScrollPane(list);
        listScrollPane.setBounds(100, 150, 800, 500);
        add(listScrollPane);

        homeButton = new JButton("Home");
        homeButton.setBounds(404, 699, 117, 29);
        add(homeButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(homeButton)) {
                    SearchResultView.this.viewManagerModel.setActiveView(SearchResultView.this.homeViewModel.getViewName());
                    SearchResultView.this.viewManagerModel.firePropertyChanged();
                }
            }
        });

        
        lblNewLabel = new JLabel("Search Results");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 24));
        lblNewLabel.setBounds(410, 83, 210, 33);
        add(lblNewLabel);

        this.list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = SearchResultView.this.list.locationToIndex(e.getPoint());
                    String itemId = SearchResultView.this.searchResultViewModel.getState()
                        .getFilteredItems()
                        .get(index)
                        .getId();
                    Student currentStudent = searchResultViewModel.getState().getCurrentStudent();
                    SearchResultView.this.viewItemController.execute(
                            itemId, currentStudent);
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
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("searchResultState")) {
            SearchResultState state = (SearchResultState) evt.getNewValue();
            updateItemList(state.getFilteredItems());
        }
    }

    private void updateItemList(ArrayList<Item> items) {
        DefaultListModel<String> listItemsModel = new DefaultListModel<String>();

        for (Item item : items) {
            listItemsModel.addElement(
                    String.format("%s %s", item.getName(), item.getPrice()));
        }

        this.list.setModel(listItemsModel);
    }
}
