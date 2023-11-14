package use_case.viewing_item;

import interface_adapter.viewing_item.ViewingItemPresenter;

public class ViewingItemInteractor implements ViewingItemInputBoundary {
    ViewingItemOutputBoundary viewingItemPresenter;

    public ViewingItemInteractor(ViewingItemPresenter viewingItemPresenter) {
        this.viewingItemPresenter = viewingItemPresenter;
    }

    @Override
    public void execute(ViewingItemInputData viewingItemData) {
        viewingItemPresenter.prepareViewItemView(
                new ViewingItemOutputData(viewingItemData.getItemId()));
    }
}
