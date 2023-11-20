package use_case.view_item;

public interface ViewItemOutputBoundary {
    void prepareSuccessView(ViewItemOutputData viewItemOutputData);

    void prepareFailView(String error);
}
