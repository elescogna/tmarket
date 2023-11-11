package interface_adapter.create_order;

import interface_adapter.ViewManagerModel;
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
        ViewItemState viewItemState = viewItemViewModel.getState();
        this.viewItemViewModel.setState(viewItemState);
        viewItemViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewItemViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        CreateOrderState createOrderState = createOrderViewModel.getState();
        createOrderState.setEmailError(message);
        createOrderViewModel.firePropertyChanged();
    }
}
