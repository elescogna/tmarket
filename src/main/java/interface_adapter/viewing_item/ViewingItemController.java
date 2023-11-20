package interface_adapter.viewing_item;

import use_case.viewing_item.ViewingItemInputBoundary;
import use_case.viewing_item.ViewingItemInputData;

public class ViewingItemController {
    final ViewingItemInputBoundary viewingItemInteractor;

    public ViewingItemController(ViewingItemInputBoundary viewItemInteractor) {
        this.viewingItemInteractor = viewItemInteractor;
    }

    public void execute(String itemId) {
        ViewingItemInputData viewItemInputData = new ViewingItemInputData(itemId);

        this.viewingItemInteractor.execute(viewItemInputData);
    }
}
