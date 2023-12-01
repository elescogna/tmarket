package use_case.create_order;

public interface CreateOrderOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String message);
}
