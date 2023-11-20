package interface_adapter.viewing_item;
import interface_adapter.ViewManagerModel;
import interface_adapter.view_item.ViewItemViewModel;
import use_case.viewing_item.ViewingItemOutputBoundary;
import use_case.viewing_item.ViewingItemOutputData;

public class ViewingItemPresenter implements ViewingItemOutputBoundary {
    private final ViewItemViewModel viewItemViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewingItemPresenter(ViewItemViewModel viewItemViewModel,
            ViewManagerModel viewManagerModel) {
        this.viewItemViewModel = viewItemViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareViewItemView(ViewingItemOutputData viewingItemOutputData) {
        viewItemViewModel.getState().setNextItemId(viewingItemOutputData.getItemId());
        viewItemViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewItemViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
