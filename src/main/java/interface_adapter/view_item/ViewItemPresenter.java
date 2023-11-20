package interface_adapter.view_item;
import use_case.view_item.ViewItemOutputBoundary;
import use_case.view_item.ViewItemOutputData;

public class ViewItemPresenter implements ViewItemOutputBoundary {
    private final ViewItemViewModel viewItemViewModel;

    public ViewItemPresenter(ViewItemViewModel viewItemViewModel) {
        this.viewItemViewModel = viewItemViewModel;
    }

    @Override
    public void prepareSuccessView(ViewItemOutputData response) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItem(response.getItemToShow());
        this.viewItemViewModel.setState(viewItemState);

        viewItemViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ViewItemState viewItemState = viewItemViewModel.getState();
        viewItemState.setCurrentItemError(error);
        this.viewItemViewModel.setState(viewItemState);

        viewItemViewModel.firePropertyChanged();
    }
}
