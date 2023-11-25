package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.post.PostPresenter;
import interface_adapter.post.PostViewModel;
import interface_adapter.post.PostController;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.post.*;
import use_case.posting.PostingInputBoundary;
import view.PostView;

import javax.swing.*;
import java.io.IOException;

public class PostUseCaseFactory {
    private PostUseCaseFactory() {}
    public static PostView create(PostViewModel postViewModel,
                                  ViewManagerModel viewManagerModel,
                                  HomeViewModel homeViewModel, FurniturePostDataAccessInterface furniturePostDataAccessInterface,
                                  ClothingPostDataAccessInterface clothingPostDataAccessInterface, TechnologyPostDataAccessInterface technologyPostDataAccessInterface,
                                  SchoolItemPostDataAccessInterface schoolItemPostDataAccessInterface, StudentDataAccessInterface studentDataAccessInterface) {
    try {
        PostController postController =
                createPostUseCase(viewManagerModel, postViewModel, homeViewModel, furniturePostDataAccessInterface, clothingPostDataAccessInterface, schoolItemPostDataAccessInterface,
                technologyPostDataAccessInterface, studentDataAccessInterface);
        GoHomeController goHomeController =
                createGoHomeUseCase(viewManagerModel, homeViewModel);

        return new PostView(goHomeController, postController, postViewModel);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }

        return null;
        }

    private static GoHomeController createGoHomeUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

        GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

        return new GoHomeController(goHomeInteractor);
    }

    private static PostController createPostUseCase(ViewManagerModel viewManagerModel,
                                                    PostViewModel postViewModel,
                                                    HomeViewModel homeViewModel,
                                                    FurniturePostDataAccessInterface furniturePostDataAccessInterface,
                                                    ClothingPostDataAccessInterface clothingPostDataAccessInterface,
                                                    SchoolItemPostDataAccessInterface schoolItemPostDataAccessInterface,
                                                    TechnologyPostDataAccessInterface technologyPostDataAccessInterface,
                                                    StudentDataAccessInterface studentDataAccessInterface)

        throws IOException {
        PostOutputBoundary postOutputBoundary = new PostPresenter(viewManagerModel, postViewModel, homeViewModel);
        PostInputBoundary  postInteractor= new PostInteractor(clothingPostDataAccessInterface, furniturePostDataAccessInterface, schoolItemPostDataAccessInterface,
                technologyPostDataAccessInterface, studentDataAccessInterface, postOutputBoundary);

        return new PostController(postInteractor);
    }




}
