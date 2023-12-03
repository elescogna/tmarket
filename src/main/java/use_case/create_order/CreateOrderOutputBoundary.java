package use_case.create_order;

public interface CreateOrderOutputBoundary {
    void prepareSuccessView(CreateOrderOutputData outputData);
    void prepareFailView(String message);

}
