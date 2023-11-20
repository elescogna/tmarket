package use_case.login;

public interface LoginOutputBoundary {
    public void prepareSuccessView(LoginOutputData response);
    public void prepareFailView(String error);
}
