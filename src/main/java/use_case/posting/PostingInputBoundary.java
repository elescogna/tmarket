package use_case.posting;

import use_case.post.PostInputData;

public interface PostingInputBoundary {
    void execute(PostInputData postInputData);
}
