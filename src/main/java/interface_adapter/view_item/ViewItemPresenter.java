package interface_adapter.view_item;
import interface_adapter.ViewManagerModel;
import use_case.view_item.ViewItemOutputBoundary;
import use_case.view_item.ViewItemOutputData;

public class ViewItemPresenter implements ViewItemOutputBoundary {
    private final ViewItemViewModel viewItemViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewItemPresenter(ViewManagerModel viewManagerModel,
            ViewItemViewModel viewItemViewModel) {
        this.viewItemViewModel = viewItemViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewItemOutputData response) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItem(response.getItemToShow());
        this.viewItemViewModel.setState(viewItemState);

        viewItemViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("view_item");
    }

    @Override
    public void prepareFailView(String error) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItemError(error);
        this.viewItemViewModel.setState(viewItemState);

        viewItemViewModel.firePropertyChanged();
    }
}
