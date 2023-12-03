package view;

import entities.Item;
import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.post.PostViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.searching.SearchingController;
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
import java.util.HashMap;
import javax.imageio.ImageIO;
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
    private JButton btnLogout;
    private JLabel lblTitle;
    private JScrollPane scrollPaneItemsScrollPane;
    private JList<String> listItems;
    private HomeController homeController;
    private HomeViewModel homeViewModel;
    private PostViewModel postViewModel;
    private ViewManagerModel viewManagerModel;
    private JButton btnRefresh;
    private ProfileController profileController;
    private SearchingController searchingController;
    private ViewItemController viewItemController;
    private Image backgroundImage;
    private ViewManagerModel viewManagerModel;

    /**
     * Create the panel.
     */
    public HomeView(HomeViewModel homeViewModel, HomeController homeController,
            PostViewModel postViewModel,
            ProfileController profileController,
            SearchingController searchingController,
            ViewItemController viewItemController,
            ViewManagerModel viewManagerModel) {
        setBackground(new Color(0, 0, 0));
        this.setLayout(null);

        this.homeController = homeController;
        this.homeViewModel = homeViewModel;
        this.profileController = profileController;
        this.searchingController = searchingController;
        this.viewItemController = viewItemController;
        this.postViewModel = postViewModel;
        this.viewManagerModel = viewManagerModel;

        try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeComponents();
        addComponents();
        addActionListeners();

        // updating the list right away
        homeController.execute();
        this.updateItemsList();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void initializeComponents() {
        btnPost = new JButton("Post");
        btnPost.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        btnPost.setBounds(690, 241, 124, 47);
        btnProfile = new JButton("Profile");
        btnProfile.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        btnProfile.setBounds(690, 445, 124, 47);
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        btnSearch.setBounds(690, 344, 124, 47);
        lblTitle = new JLabel("TmarkeT");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 33));
        lblTitle.setBounds(130, 79, 430, 37);
        listItems = new JList<String>();
        scrollPaneItemsScrollPane = new JScrollPane(listItems);
        scrollPaneItemsScrollPane.setBounds(100, 150, 500, 600);
        btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        btnRefresh.setBounds(690, 544, 124, 47);
        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
        btnLogout.setBounds(690, 650, 124, 47);
    }

    public void addComponents() {
        add(btnPost);
        add(btnProfile);
        add(btnSearch);
        add(lblTitle);
        add(scrollPaneItemsScrollPane);
        add(btnRefresh);
        add(btnLogout);
    }

    public void addActionListeners() {
        btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = HomeView.this.homeViewModel.getState().getStudent();

                HomeView.this.postViewModel.getState().setStudent(student);
                HomeView.this.postViewModel.firePropertyChanged();

                HomeView.this.viewManagerModel.setActiveView(
                        HomeView.this.postViewModel.getViewName());
                HomeView.this.viewManagerModel.firePropertyChanged();
            }
        });

        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnProfile)) {
                    HomeState state = homeViewModel.getState();
                    Student student = state.getStudent();
                    profileController.execute(student);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnSearch)) {
                    Student currentStudent = homeViewModel.getState().getStudent();
                    searchingController.execute(currentStudent);
                }
            }
        });

        listItems.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listItems.locationToIndex(e.getPoint());

                    HomeState homeState = HomeView.this.homeViewModel.getState();

                    String itemId = homeState.getAllPosts().get(index).getId();
                    Student currentStudent = homeState.getStudent();

                    HomeView.this.viewItemController.execute(itemId, currentStudent);
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
                HomeView.this.updateItemsList();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btnLogout)) {
                    viewManagerModel.setActiveView("sign_up");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
    }

    public void updateItemsList() {
        ArrayList<Item> items = this.homeViewModel.getState().getAllPosts();

        DefaultListModel<String> listItemsModel = new DefaultListModel<String>();

        for (Item item : items) {
            if (!item.isSoldYet()) {
                listItemsModel.addElement(
                        String.format("%s %s", item.getName(), item.getPrice()));
            }
        }

        this.listItems.setModel(listItemsModel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateItemsList();
    }
}
