package interface_adapter.create_order;

import interface_adapter.ViewManagerModel;
import use_case.create_order.CreateOrderOutputBoundary;
import use_case.create_order.CreateOrderOutputData;

public class CreateOrderPresenter implements CreateOrderOutputBoundary {
    private CreateOrderViewModel createOrderViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateOrderPresenter(ViewManagerModel viewManagerModel,
                                 CreateOrderViewModel createOrderViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createOrderViewModel = createOrderViewModel;
    }

    /**
     * Prepares to display the CreateOrderView with the output data given.
     *
     * @param outputData the output data with which to display the CreateOrderView
     */
    @Override
    public void prepareSuccessView(CreateOrderOutputData outputData) {
        createOrderViewModel.getState().setItem(outputData.getItem());
        createOrderViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("home");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to display the error message given.
     *
     * @param message the error message to display
     */
    @Override
    public void prepareFailView(String message) {
        CreateOrderState createOrderState = createOrderViewModel.getState();
        createOrderState.setEmailError(message);
        createOrderViewModel.firePropertyChanged();
    }
}
