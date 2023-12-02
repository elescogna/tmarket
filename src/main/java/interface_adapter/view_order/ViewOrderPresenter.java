package interface_adapter.view_order;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.view_order.ViewOrderOutputBoundary;
import use_case.view_order.ViewOrderOutputData;

public class ViewOrderPresenter implements ViewOrderOutputBoundary {
    private final ViewOrderViewModel viewOrderViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public ViewOrderPresenter(ViewOrderViewModel viewOrderViewModel,
            ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel) {
        this.viewOrderViewModel = viewOrderViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(ViewOrderOutputData response) {
        ViewOrderState viewOrderState = viewOrderViewModel.getState();

        viewOrderState.setCurrentOrder(response.getOrderToShow());
        viewOrderState.setCurrentItemNameToShow(response.getItemName());
        viewOrderState.setCurrentDirections(response.getDirections());

        this.viewOrderViewModel.setState(viewOrderState);
        viewOrderViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewOrderViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ViewOrderState viewOrderState = viewOrderViewModel.getState();
        viewOrderState.setCurrentOrderError(error);

        this.viewOrderViewModel.setState(viewOrderState);
        viewOrderViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
