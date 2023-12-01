package interface_adapter.posting;

import entities.Student;
import use_case.posting.PostingInputBoundary;
import use_case.posting.PostingInputData;

public class PostingController {

    final PostingInputBoundary postingInteractor;
    public PostingController(PostingInputBoundary postingInteractor) {
        this.postingInteractor = postingInteractor;
    }

    public void execute(Student currentStudent) {
        this.postingInteractor.execute(new PostingInputData(currentStudent));
    }
}
