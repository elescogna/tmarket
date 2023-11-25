package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.post.PostContoller;
import interface_adapter.post.PostViewModel;
import interface_adapter.posting.PostingController;
import interface_adapter.posting.PostingPresenter;
import interface_adapter.posting.PostingViewModel;
import use_case.post.*;
import use_case.posting.PostingInputBoundary;
import use_case.posting.PostingInteractor;
import use_case.posting.PostingOutputBoundary;
import view.HomeView;
import view.PostingView;

import javax.swing.*;
import java.io.IOException;

public class PostingUseCaseFactory {
    private PostingUseCaseFactory(){}
    public static PostingView
        create(ViewManagerModel viewManagerModel, PostingViewModel postingViewModel,
               PostViewModel postViewModel, PostContoller postContoller, FurniturePostDataAccessInterface furniturePostDataAccessInterface,
               ClothingPostDataAccessInterface clothingPostDataAccessInterface, TechnologyPostDataAccessInterface technologyPostDataAccessInterface,
               SchoolItemPostDataAccessInterface schoolItemPostDataAccessInterface){
        try{
            PostingController postController = createPostUseCase(viewManagerModel, postingViewModel,
                    postViewModel, furniturePostDataAccessInterface, clothingPostDataAccessInterface,
                    technologyPostDataAccessInterface, schoolItemPostDataAccessInterface
                    );
            return new PostView(postingViewModel, postContoller);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }
        return null;
    }

    private static PostController createPostingUseCase(ViewManagerModel viewManagerModel,
                                                    PostingViewModel postingViewModel,
                                                    PostViewModel postViewModel,
                                                    FurniturePostDataAccessInterface furniturePostDataAccessInterface,
                                                    ClothingPostDataAccessInterface clothingPostDataAccessInterface,
                                                    TechnologyPostDataAccessInterface technologyPostDataAccessInterface,
                                                    SchoolItemPostDataAccessInterface schoolItemPostDataAccessInterface)
        throws IOException{
        PostingOutputBoundary postingOutputBoundary =
                new PostingPresenter(viewManagerModel, postingViewModel, postViewModel);
        PostingInputBoundary postingInteractor =
                new PostingInteractor();
                return new PostingController(postingInteractor);
    }
}
