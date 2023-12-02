package use_case.view_order;

public interface ViewOrderOutputBoundary {
    void prepareSuccessView(ViewOrderOutputData viewItemOutputData);

    void prepareFailView(String error);
}
