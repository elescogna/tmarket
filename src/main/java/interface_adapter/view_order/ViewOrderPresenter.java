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

    /**
     * Prepares the view order view with the info from the output data given.
     *
     * @param response the ViewOrderViewModel containing the correct info to
     * display on the login screen.
     */
    @Override
    public void prepareSuccessView(ViewOrderOutputData response) {
        ViewOrderState viewOrderState = viewOrderViewModel.getState();

        viewOrderState.setCurrentOrder(response.getOrderToShow());
        viewOrderState.setCurrentItemNameToShow(response.getItemName());
        viewOrderState.setCurrentDirections(response.getDirections());
        viewOrderState.setCurrentOrderItemImage(response.getOrderItemImageIcon());

        this.viewOrderViewModel.setState(viewOrderState);
        viewOrderViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewOrderViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *  Prepares to display the error given.
     *
     * @param error the error to display
     */
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
