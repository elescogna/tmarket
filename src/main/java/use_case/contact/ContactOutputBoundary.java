package use_case.contact;

public interface ContactOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
