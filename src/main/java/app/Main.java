package app;

import data_access.AtlasClothingDataAccessObject;
import data_access.AtlasFurnitureDataAccessObject;
import data_access.AtlasOrderDataAccessObject;
import data_access.AtlasSchoolItemDataAccessObject;
import data_access.AtlasStudentDataAccessObject;
import data_access.AtlasTechnologyDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.post.PostViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import interface_adapter.view_order.ViewOrderViewModel;
import java.awt.CardLayout;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import view.ContactView;
import view.CreateOrderView;
import view.HomeView;
import view.LoginView;
import view.PostView;
import view.ProfileView;
import view.SearchResultView;
import view.SearchView;
import view.SignupView;
import view.ViewItemView;
import view.ViewManager;
import view.ViewOrderView;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("TMarkeT");
        application.setResizable(false);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // create all view models
        HomeViewModel homeViewModel = new HomeViewModel();
        PostViewModel postViewModel = new PostViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ContactViewModel contactViewModel = new ContactViewModel();
        ViewItemViewModel viewItemViewModel = new ViewItemViewModel();
        CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ViewOrderViewModel viewOrderViewModel = new ViewOrderViewModel();

        // create all DAOs
        AtlasOrderDataAccessObject orderDataAccessObject;
        AtlasStudentDataAccessObject studentDataAccessObject;
        AtlasClothingDataAccessObject clothingDataAccessObject;
        AtlasFurnitureDataAccessObject furnitureDataAccessObject;
        AtlasSchoolItemDataAccessObject schoolItemDataAccessObject;
        AtlasTechnologyDataAccessObject technologyDataAccessObject;

        orderDataAccessObject = new AtlasOrderDataAccessObject();
        studentDataAccessObject = new AtlasStudentDataAccessObject();
        clothingDataAccessObject = new AtlasClothingDataAccessObject();
        furnitureDataAccessObject = new AtlasFurnitureDataAccessObject();
        schoolItemDataAccessObject = new AtlasSchoolItemDataAccessObject();
        technologyDataAccessObject = new AtlasTechnologyDataAccessObject();

        SearchResultView searchResultView = SearchResultUseCaseFactory.create(
                viewManagerModel, homeViewModel, searchResultViewModel,
                viewItemViewModel, clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject);
        searchResultView.setPreferredSize(new Dimension(1000, 800));
        views.add(searchResultView, searchResultViewModel.getViewName());

        SearchView searchView = SearchUseCaseFactory.create(
                viewManagerModel, searchViewModel, homeViewModel, searchResultViewModel,
                clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject);
        searchView.setPreferredSize(new Dimension(1000, 800));
        views.add(searchView, searchViewModel.getViewName());

        ViewItemView viewItemView = ViewItemUseCaseFactory.create(
                viewManagerModel, homeViewModel, viewItemViewModel, contactViewModel,
                createOrderViewModel, clothingDataAccessObject,
                furnitureDataAccessObject, schoolItemDataAccessObject,
                technologyDataAccessObject, clothingDataAccessObject,
                furnitureDataAccessObject, schoolItemDataAccessObject,
                technologyDataAccessObject);
        viewItemView.setPreferredSize(new Dimension(1000,800));
        views.add(viewItemView, viewItemViewModel.getViewName());

        ContactView contactView =
                ContactUseCaseFactory.create(contactViewModel, viewManagerModel,
                        homeViewModel, studentDataAccessObject);
        contactView.setPreferredSize(new Dimension(1000, 800));
        views.add(contactView, contactViewModel.getViewName());

        CreateOrderView createOrderView = CreateOrderUseCaseFactory.create(
                viewManagerModel, viewItemViewModel, createOrderViewModel,
                homeViewModel, orderDataAccessObject, studentDataAccessObject,
                clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject);
        createOrderView.setPreferredSize(new Dimension(1000, 800));
        views.add(createOrderView, createOrderViewModel.getViewName());

        HomeView homeView = HomeUseCaseFactory.create(
                viewManagerModel, homeViewModel, viewItemViewModel, searchViewModel,
                profileViewModel, postViewModel, clothingDataAccessObject,
                furnitureDataAccessObject, schoolItemDataAccessObject,
                technologyDataAccessObject, studentDataAccessObject,
                clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject,
                orderDataAccessObject, clothingDataAccessObject,
                furnitureDataAccessObject, schoolItemDataAccessObject,
                technologyDataAccessObject);
        homeView.setPreferredSize(new Dimension(1000,800));
        views.add(homeView, homeViewModel.getViewName());

        LoginView loginView =
            LoginUseCaseFactory.create(loginViewModel, homeViewModel,
                    studentDataAccessObject, viewManagerModel);
        views.add(loginView, loginViewModel.getViewName());

        SignupView signupView =
            SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                    signupViewModel, studentDataAccessObject);
        signupView.setPreferredSize(new Dimension(1000, 800));
        views.add(signupView, signupViewModel.getViewName());

        PostView postView = PostUseCaseFactory.create(
                postViewModel, viewManagerModel, homeViewModel,
                furnitureDataAccessObject, clothingDataAccessObject,
                technologyDataAccessObject, schoolItemDataAccessObject);
        postView.setPreferredSize(new Dimension(1000, 800));
        views.add(postView, postViewModel.getViewName());

        ProfileView profileView = ProfileUseCaseFactory.create(
                profileViewModel, viewManagerModel, homeViewModel, viewOrderViewModel,
                studentDataAccessObject, clothingDataAccessObject,
                furnitureDataAccessObject, schoolItemDataAccessObject,
                technologyDataAccessObject, orderDataAccessObject,
                orderDataAccessObject);
        profileView.setPreferredSize(new Dimension(1000,800));
        views.add(profileView, profileViewModel.getViewName());

        ViewOrderView viewOrderView = ViewOrderUseCaseFactory.create(
                viewManagerModel, homeViewModel, viewOrderViewModel);
        views.add(viewOrderView, viewOrderViewModel.getViewName());

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
