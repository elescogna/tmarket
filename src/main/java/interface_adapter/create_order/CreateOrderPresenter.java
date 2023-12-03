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

    @Override
    public void prepareSuccessView(CreateOrderOutputData outputData) {
        createOrderViewModel.getState().setItem(outputData.getItem());
        createOrderViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("home");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        CreateOrderState createOrderState = createOrderViewModel.getState();
        createOrderState.setEmailError(message);
        createOrderViewModel.firePropertyChanged();
    }
}
