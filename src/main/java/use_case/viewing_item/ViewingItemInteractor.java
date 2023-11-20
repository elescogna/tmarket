package use_case.viewing_item;

public class ViewingItemInteractor implements ViewingItemInputBoundary {
    ViewingItemOutputBoundary viewingItemPresenter;

    public ViewingItemInteractor(ViewingItemOutputBoundary viewingItemPresenter) {
        this.viewingItemPresenter = viewingItemPresenter;
    }

    @Override
    public void execute(ViewingItemInputData viewingItemData) {
        viewingItemPresenter.prepareViewItemView(
                new ViewingItemOutputData(viewingItemData.getItemId()));
    }
}
