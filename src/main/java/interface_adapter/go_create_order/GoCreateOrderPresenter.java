package interface_adapter.go_create_order;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderState;
import interface_adapter.create_order.CreateOrderViewModel;
import use_case.go_create_order.GoCreateOrderOutputBoundary;
import use_case.go_create_order.GoCreateOrderOutputData;

public class GoCreateOrderPresenter implements GoCreateOrderOutputBoundary {
    private CreateOrderViewModel createOrderViewModel;
    private ViewManagerModel viewManagerModel;

    public GoCreateOrderPresenter(CreateOrderViewModel createOrderViewModel, ViewManagerModel viewManagerModel) {
        this.createOrderViewModel = createOrderViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GoCreateOrderOutputData goCreateOrderOutputData) {
        CreateOrderState createOrderState = createOrderViewModel.getState();
        createOrderState.setStudent(goCreateOrderOutputData.getUser());
        createOrderState.setItem(goCreateOrderOutputData.getItem());
        createOrderViewModel.setState(createOrderState);
        createOrderViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createOrderViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
