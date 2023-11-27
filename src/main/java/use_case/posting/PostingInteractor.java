package use_case.posting;

import java.io.IOException;

public class PostingInteractor implements PostingInputBoundary {
    final PostingOutputBoundary postingPresenter;

    public PostingInteractor(PostingOutputBoundary postingPresenter) {
        this.postingPresenter = postingPresenter;
    }
    public void execute(){
      postingPresenter.prepareSuccessView();
    }
}
