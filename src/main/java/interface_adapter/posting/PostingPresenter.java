package interface_adapter.posting;

import interface_adapter.ViewManagerModel;
import interface_adapter.post.PostViewModel;
import use_case.posting.PostingOutputBoundary;

public class PostingPresenter implements PostingOutputBoundary {
    private final PostViewModel postViewModel;
    private final PostingViewModel postingViewModel;
    private ViewManagerModel viewManagerModel;
    public PostingPresenter(ViewManagerModel viewManagerModel,
            PostViewModel postViewModel, PostingViewModel
            postingViewModel){
        this.postingViewModel = postingViewModel;
        this.postViewModel = postViewModel;
        this.viewManagerModel = viewManagerModel;
            }

    @Override
    public void prepareSuccessView() {
        PostingState postingState = postingViewModel.getState();
        postViewModel.getState().setStudent(postingState.getStudent());
        postViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(postViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
