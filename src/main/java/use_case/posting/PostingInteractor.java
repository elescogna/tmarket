package use_case.posting;

import java.io.IOException;

public class PostingInteractor implements PostingInputBoundary {
    final PostingOutputBoundary postingPresenter;

    public PostingInteractor(PostingOutputBoundary postingPresenter) {
        this.postingPresenter = postingPresenter;
    }

    @Override
    public void execute(PostingInputData inputData) {
        this.postingPresenter.prepareSuccessView(
                new PostingOutputData(inputData.getCurrentStudent()));
    }
}
