package interface_adapter.create_order;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_item.ViewItemState;
import interface_adapter.view_item.ViewItemViewModel;
import use_case.create_order.CreateOrderOutputBoundary;

public class CreateOrderPresenter implements CreateOrderOutputBoundary {
    private CreateOrderViewModel createOrderViewModel;
    private ViewItemViewModel viewItemViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateOrderPresenter(ViewManagerModel viewManagerModel,
                                 CreateOrderViewModel createOrderViewModel,
                                 ViewItemViewModel viewItemViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createOrderViewModel = createOrderViewModel;
        this.viewItemViewModel = viewItemViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView("home"); // TODO: need to set the active view to view order
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        CreateOrderState createOrderState = createOrderViewModel.getState();
        createOrderState.setEmailError(message);
        createOrderViewModel.firePropertyChanged();
    }
}
