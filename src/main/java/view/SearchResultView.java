package view;

import entities.Item;
import entities.Student;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.view_item.ViewItemController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class SearchResultView extends JPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private final JList list;
    private JScrollPane listScrollPane;
    private JButton homeButton;
    private GoHomeController goHomeController;
    private SearchResultViewModel searchResultViewModel;
    private ViewItemController viewItemController;
    private JLabel lblNewLabel;

    /**
     * Create the panel.
     */
    public SearchResultView(GoHomeController goHomeController,
            SearchResultViewModel searchResultViewModel,
            ViewItemController viewItemController) {
    	setBackground(new Color(0, 0, 0));
        this.setLayout(null);
        this.goHomeController = goHomeController;
        this.searchResultViewModel = searchResultViewModel;
        this.viewItemController = viewItemController;
        this.searchResultViewModel.addPropertyChangeListener(this);

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
                    SearchResultView.this.goHomeController.execute();
                }
            }
        });

        
        lblNewLabel = new JLabel("Search Results");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 24));
        lblNewLabel.setBounds(410, 83, 180, 33);
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
