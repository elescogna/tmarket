package interface_adapter.view_item;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.view_item.ViewItemOutputBoundary;
import use_case.view_item.ViewItemOutputData;

public class ViewItemPresenter implements ViewItemOutputBoundary {
    private final ViewItemViewModel viewItemViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public ViewItemPresenter(ViewItemViewModel viewItemViewModel,
            ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel) {
        this.viewItemViewModel = viewItemViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    /**
     * Prepares the view item view with the info from the output data given.
     *
     * @param response the ViewItemOutputData containing the correct info to display on the view screen.
     */
    @Override
    public void prepareSuccessView(ViewItemOutputData response) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItem(response.getItemToShow());
        viewItemState.setCurrentStudent(response.getCurrentStudent());
        viewItemState.setCurrentItemImage(response.getItemImageIcon());

        this.viewItemViewModel.setState(viewItemState);
        viewItemViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewItemViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to display the error message given.
     *
     * @param error the error to display
     */
    @Override
    public void prepareFailView(String error) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItemError(error);

        this.viewItemViewModel.setState(viewItemState);
        viewItemViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
