package use_case.profile;

public interface ProfileOutputBoundary {
    void prepareSuccessView(ProfileOutputData profileOutputData);
    void prepareFailView(String error);
}
