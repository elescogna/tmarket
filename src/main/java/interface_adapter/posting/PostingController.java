package interface_adapter.posting;

import use_case.posting.PostingInputBoundary;

public class PostingController {

    final PostingInputBoundary postingInteractor;
    public PostingController(PostingInputBoundary postingInteractor) {this.postingInteractor = postingInteractor;}
    public void execute(){
        this.postingInteractor.execute();
    }
}
