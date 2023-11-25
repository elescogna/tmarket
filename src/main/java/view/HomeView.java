package view;

import entities.Item;
import entities.Student;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.posting.PostingController;
import interface_adapter.posting.PostingState;
import interface_adapter.posting.PostingViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.searching.SearchingController;
import interface_adapter.viewing_item.ViewingItemController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class HomeView extends JPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private JButton btnPost;
    private JButton btnProfile;
    private JButton btnSearch;
    private JLabel lblTitle;
    private JScrollPane scrollPaneItemsScrollPane;
    private JList<String> listItems;
    private HomeController homeController;
    private ViewingItemController viewingItemController;
    private HomeViewModel homeViewModel;
    private JButton btnRefresh;
    private PostingController postingController;
    private ProfileController profileController;
    private SearchingController searchingController;


    /**
     * Create the panel.
     */
    public HomeView(HomeViewModel homeViewModel, HomeController homeController,
                    ViewingItemController viewingItemController, PostingController postingController,
                    ProfileController profileController, SearchingController searchingController) {
        this.setLayout(null);

        this.homeController = homeController;
        this.homeViewModel = homeViewModel;
        this.postingController = postingController;
        this.profileController = profileController;
        this.searchingController = searchingController;

        initializeComponents();
        addComponents();
        addActionListeners();

        // updating the list right away
        homeController.execute();
        this.updateItemsList();

        // TODO: add the the code that actually makes the view work (using the
        // contructor parameters)
    }

    public void initializeComponents() {
        btnPost = new JButton("Post");
        btnPost.setBounds(318, 67, 124, 47);
        btnProfile = new JButton("Profile");
        btnProfile.setBounds(318, 183, 124, 47);
        btnSearch = new JButton("Search");
        btnSearch.setBounds(318, 125, 124, 47);
        lblTitle = new JLabel("TmarkeT");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 33));
        lblTitle.setBounds(10, 22, 430, 37);
        listItems = new JList<String>();
        scrollPaneItemsScrollPane = new JScrollPane(listItems);
        scrollPaneItemsScrollPane.setBounds(21, 70, 285, 219);
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(318, 242, 124, 47);
    }

    public void addComponents() {
        add(btnPost);
        add(btnProfile);
        add(btnSearch);
        add(lblTitle);
        add(scrollPaneItemsScrollPane);
        add(btnRefresh);
    }

    public void addActionListeners() {
        btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnPost)){
                    postingController.execute();
                    // TODO: switch views
                }
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnProfile)){
                    HomeState state = homeViewModel.getState();
                    Student student = state.getUser();
                    profileController.execute(student);
                    // TODO: switch views
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnSearch)){
                    Student currentStudent = homeViewModel.getState().getUser();
                    searchingController.execute(currentStudent);
                }
            }
        });

        listItems.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listItems.locationToIndex(e.getPoint());
                    String itemId = HomeView.this.homeViewModel.getState()
                            .getWantedPosts()
                            .get(index)
                            .getId();

                    HomeView.this.viewingItemController.execute(itemId);
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

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeView.this.homeController.execute();
            }
        });
    }

    public void updateItemsList() {
        ArrayList<Item> items = this.homeViewModel.getState().getWantedPosts();

        DefaultListModel<String> listItemsModel = new DefaultListModel<String>();

        for (Item item : items) {
            listItemsModel.addElement(String.format("%s %s", item.getName(),
                    item.getPrice()));
        }

        this.listItems.setModel(listItemsModel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateItemsList();
    }
}