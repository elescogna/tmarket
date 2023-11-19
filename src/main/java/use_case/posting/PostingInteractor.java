package use_case.posting;

import java.io.IOException;

public class PostingInteractor {
    final PostingOutputBoundary postingPresenter;

    public PostingInteractor(PostingOutputBoundary postingPresenter) {
        this.postingPresenter = postingPresenter;
    }
    public void execute(PostingInputBoundary postingInputData){
      postingPresenter.prepareSuccessView();
    }
}
