package use_case.post;

public interface PostOutputBoundary {
    void prepareSuccessView(PostOutputData postOutputData);

    void prepareFailView(String s);
}
