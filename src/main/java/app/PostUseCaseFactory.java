package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.post.PostController;
import interface_adapter.post.PostPresenter;
import interface_adapter.post.PostViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.post.*;
import use_case.posting.PostingInputBoundary;
import view.PostView;

public class PostUseCaseFactory {
    private PostUseCaseFactory() {}
    public static PostView
        create(PostViewModel postViewModel, ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                FurniturePostDataAccessInterface furniturePostDataAccessObject,
                ClothingPostDataAccessInterface clothingPostDataAccessObject,
                TechnologyPostDataAccessInterface technologyPostDataAccessObject,
                SchoolItemPostDataAccessInterface schoolItemPostDataAccessObject) {
            try {
                PostController postController = createPostUseCase(
                        viewManagerModel, postViewModel, homeViewModel,
                        furniturePostDataAccessObject, clothingPostDataAccessObject,
                        schoolItemPostDataAccessObject, technologyPostDataAccessObject);
                GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);

                return new PostView(goHomeController, postController, postViewModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static GoHomeController
        createGoHomeUseCase(ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel) {
            GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

            GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

            return new GoHomeController(goHomeInteractor);
        }

    private static PostController createPostUseCase(
            ViewManagerModel viewManagerModel, PostViewModel postViewModel,
            HomeViewModel homeViewModel,
            FurniturePostDataAccessInterface furniturePostDataAccessInterface,
            ClothingPostDataAccessInterface clothingPostDataAccessInterface,
            SchoolItemPostDataAccessInterface schoolItemPostDataAccessInterface,
            TechnologyPostDataAccessInterface technologyPostDataAccessInterface)

            throws IOException {
            PostOutputBoundary postOutputBoundary =
                new PostPresenter(viewManagerModel, homeViewModel);
            PostInputBoundary postInteractor = new PostInteractor(
                    clothingPostDataAccessInterface, furniturePostDataAccessInterface,
                    schoolItemPostDataAccessInterface, technologyPostDataAccessInterface,
                    postOutputBoundary);

            return new PostController(postInteractor);
            }
}
