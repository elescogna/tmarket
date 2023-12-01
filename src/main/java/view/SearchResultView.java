package view;

import entities.Item;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.view_item.ViewItemController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class SearchResultView extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JList list;
    private JScrollPane listScrollPane;
    private JButton homeButton;
    private GoHomeController goHomeController;
    private SearchResultViewModel searchResultViewModel;
    private ViewItemController viewItemController;

    /**
     * Create the panel.
     */
    public SearchResultView(GoHomeController goHomeController,
            SearchResultViewModel searchResultViewModel,
            ViewItemController viewItemController) {
        this.setLayout(null);
        this.goHomeController = goHomeController;
        this.searchResultViewModel = searchResultViewModel;
        this.viewItemController = viewItemController;

        list = new JList();

        listScrollPane = new JScrollPane(list);
        listScrollPane.setBounds(36, 33, 379, 199);
        add(listScrollPane);

        homeButton = new JButton("Home");
        homeButton.setBounds(169, 244, 117, 29);
        add(homeButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(homeButton)) {
                    SearchResultView.this.goHomeController.execute();
                }
            }
        });

        ArrayList<Item> items =
            this.searchResultViewModel.getState().getFilteredItems();

        DefaultListModel<String> listItemsModel = new DefaultListModel<String>();

        for (Item item : items) {
            listItemsModel.addElement(
                    String.format("%s %s", item.getName(), item.getPrice()));
        }

        this.list.setModel(listItemsModel);

        this.list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = SearchResultView.this.list.locationToIndex(e.getPoint());
                    String itemId = SearchResultView.this.searchResultViewModel.getState()
                        .getFilteredItems()
                        .get(index)
                        .getId();

                    SearchResultView.this.viewItemController.execute(
                            itemId, null); // TODO:
                                           // change
                                           // null
                                           // to
                                           // currentStudent
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
}
