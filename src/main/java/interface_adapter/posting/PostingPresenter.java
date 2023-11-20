package interface_adapter.posting;

import interface_adapter.ViewManagerModel;
import use_case.posting.PostingOutputBoundary;

import javax.crypto.spec.PSource;

public class PostingPresenter implements PostingOutputBoundary {
    private final PostingViewModel postingViewModel;
    private ViewManagerModel viewManagerModel;
    public PostingPresenter(ViewManagerModel viewManagerModel, PostingViewModel postingViewModel ){
        this.postingViewModel = postingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        PostingState postingState = new PostingState();
        this.postingViewModel.setState(postingState);
        postingViewModel.firePropertyChanged();
    }
}
