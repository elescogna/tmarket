package app;

import data_access.AtlasStudentDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.post.PostViewModel;
import interface_adapter.posting.PostingController;
import interface_adapter.posting.PostingPresenter;
import interface_adapter.posting.PostingViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.searching.SearchingController;
import interface_adapter.searching.SearchingPresenter;
import interface_adapter.searching.SearchingViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import interface_adapter.viewing_item.ViewingItemController;
import interface_adapter.viewing_item.ViewingItemPresenter;
import java.io.IOException;
import javax.swing.*;
import use_case.home.HomeDataAccessInterface;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.posting.PostingInteractor;
import use_case.posting.PostingOutputBoundary;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.searching.SearchingInteractor;
import use_case.searching.SearchingOutputBoundary;
import use_case.viewing_item.ViewingItemInputBoundary;
import use_case.viewing_item.ViewingItemInteractor;
import use_case.viewing_item.ViewingItemOutputBoundary;
import view.HomeView;

public class HomeUseCaseFactory {

    /** Prevent instantiation. */
    private HomeUseCaseFactory() {}

    public static HomeView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                ViewItemViewModel viewItemViewModel, SearchViewModel searchViewModel,
                ProfileViewModel profileViewModel, PostViewModel postViewModel,
                HomeDataAccessInterface clothingDataAccessObject,
                HomeDataAccessInterface furnitureDataAccessObject,
                HomeDataAccessInterface schoolItemDataAccessObject,
                HomeDataAccessInterface technologyDataAccessObject,
                ProfileDataAccessInterface studentDataAccessObject) {

            try {
                HomeController homeController = createHomeUseCase(
                        viewManagerModel, homeViewModel, clothingDataAccessObject,
                        furnitureDataAccessObject, schoolItemDataAccessObject,
                        technologyDataAccessObject);

                ViewingItemController viewingItemController =
                    createViewingItemUseCase(viewItemViewModel, viewManagerModel);
                SearchingController searchingController =
                    createSearchingUseCase(searchViewModel, viewManagerModel);
                ProfileController profileController = createProfileUseCase(
                        profileViewModel, viewManagerModel, studentDataAccessObject);
                PostingController postingController = createPostingUseCase(viewManagerModel, postViewModel);

                return new HomeView(homeViewModel, homeController, viewingItemController,
                        postingController, profileController,
                        searchingController);
            } catch (IOException e) {
                // TODO: what should this actually print out?
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static HomeController
        createHomeUseCase(ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                HomeDataAccessInterface clothingDataAccessObject,
                HomeDataAccessInterface furnitureDataAccessObject,
                HomeDataAccessInterface schoolItemDataAccessInterface,
                HomeDataAccessInterface technologyDataAccessInterface)
            throws IOException {

            // Pass this method's parameters to the Presenter.
            HomeOutputBoundary homeOutputBoundary =
                new HomePresenter(viewManagerModel, homeViewModel);

            HomeInputBoundary homeInteractor =
                new HomeInteractor(clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessInterface,
                        technologyDataAccessInterface, homeOutputBoundary);

            return new HomeController(homeInteractor);
        }

    private static ViewingItemController
        createViewingItemUseCase(ViewItemViewModel viewingItemViewModel,
                ViewManagerModel viewManagerModel) {
            ViewingItemOutputBoundary viewingItemOutputBoundary =
                new ViewingItemPresenter(viewingItemViewModel, viewManagerModel);

            ViewingItemInputBoundary viewingItemInteractor =
                new ViewingItemInteractor(viewingItemOutputBoundary);

            return new ViewingItemController(viewingItemInteractor);
        }

    private static SearchingController
        createSearchingUseCase(SearchViewModel searchViewModel,
                ViewManagerModel viewManagerModel) {
            SearchingOutputBoundary searchingOutputBoundary =
                new SearchingPresenter(searchViewModel, viewManagerModel);

            SearchingInteractor searchingInteractor =
                new SearchingInteractor(searchingOutputBoundary);

            return new SearchingController(searchingInteractor);
        }

    private static ProfileController
        createProfileUseCase(ProfileViewModel profileViewModel,
                ViewManagerModel viewManagerModel,
                ProfileDataAccessInterface studentDataAccessObject) {
            ProfileOutputBoundary profilePresenter =
                new ProfilePresenter(viewManagerModel, profileViewModel);

            ProfileInteractor profileInteractor =
                new ProfileInteractor(studentDataAccessObject, profilePresenter);

            return new ProfileController(profileInteractor);
        }
    private static PostingController
        createPostingUseCase(ViewManagerModel viewManagerModel,
                PostViewModel postViewModel) {
            PostingOutputBoundary postingOutputBoundary =
                new PostingPresenter(viewManagerModel, postViewModel);

            PostingInteractor postingInteractor =
                new PostingInteractor(postingOutputBoundary);

            return new PostingController(postingInteractor);
        }
}
