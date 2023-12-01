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
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
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
import use_case.view_item.ViewItemDataAccessInterface;
import use_case.view_item.ViewItemInteractor;
import use_case.view_item.ViewItemOutputBoundary;
import view.HomeView;

public class HomeUseCaseFactory {

    /** Prevent instantiation. */
    private HomeUseCaseFactory() {}

    public static HomeView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                ViewItemViewModel viewItemViewModel, SearchViewModel searchViewModel,
                ProfileViewModel profileViewModel, PostViewModel postViewModel,
                HomeDataAccessInterface clothingHomeDataAccessObject,
                HomeDataAccessInterface furnitureHomeDataAccessObject,
                HomeDataAccessInterface schoolItemHomeDataAccessObject,
                HomeDataAccessInterface technologyHomeDataAccessObject,
                ProfileDataAccessInterface studentDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject) {

            try {
                HomeController homeController = createHomeUseCase(
                        viewManagerModel, homeViewModel, clothingHomeDataAccessObject,
                        furnitureHomeDataAccessObject, schoolItemHomeDataAccessObject,
                        technologyHomeDataAccessObject);
                ViewItemController viewItemController = createViewItemUseCase(
                        viewItemViewModel, viewManagerModel, homeViewModel,
                        clothingViewItemDataAccessObject, furnitureViewItemDataAccessObject,
                        technologyViewItemDataAccessObject,
                        schoolItemViewItemDataAccessObject);

                SearchingController searchingController =
                    createSearchingUseCase(searchViewModel, viewManagerModel);
                ProfileController profileController = createProfileUseCase(
                        profileViewModel, viewManagerModel, studentDataAccessObject);
                PostingController postingController =
                    createPostingUseCase(viewManagerModel, postViewModel);

                return new HomeView(homeViewModel, homeController, postingController,
                        profileController, searchingController,
                        viewItemController);
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
                new HomePresenter(homeViewModel);

            HomeInputBoundary homeInteractor =
                new HomeInteractor(clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessInterface,
                        technologyDataAccessInterface, homeOutputBoundary);

            return new HomeController(homeInteractor);
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

    private static ViewItemController createViewItemUseCase(
            ViewItemViewModel viewItemViewModel, ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            ViewItemDataAccessInterface clothingDataAccessObject,
            ViewItemDataAccessInterface furnitureDataAccessObject,
            ViewItemDataAccessInterface technologyDataAccessObject,
            ViewItemDataAccessInterface schoolItemDataAccessObject) {
        ViewItemOutputBoundary viewItemOutputBoundary = new ViewItemPresenter(
                viewItemViewModel, viewManagerModel, homeViewModel);

        ViewItemInteractor viewItemInteractor = new ViewItemInteractor(
                clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject,
                viewItemOutputBoundary);

        return new ViewItemController(viewItemInteractor);
            }
}
