package interface_adapter.posting;

import interface_adapter.ViewManagerModel;
import interface_adapter.post.PostViewModel;
import use_case.posting.PostingOutputBoundary;
import use_case.posting.PostingOutputData;

public class PostingPresenter implements PostingOutputBoundary {
    private final PostViewModel postViewModel;
    private ViewManagerModel viewManagerModel;

    public PostingPresenter(ViewManagerModel viewManagerModel,
            PostViewModel postViewModel) {
        this.postViewModel = postViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(PostingOutputData outputData) {
        postViewModel.getState().setStudent(outputData.getCurrentStudent());
        postViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
