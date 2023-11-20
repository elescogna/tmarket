package interface_adapter.viewing_item;

import use_case.viewing_item.ViewingItemInputBoundary;
import use_case.viewing_item.ViewingItemInputData;

public class ViewingItemController {
    final ViewingItemInputBoundary viewItemInteractor;

    public ViewingItemController(ViewingItemInputBoundary viewItemInteractor) {
        this.viewItemInteractor = viewItemInteractor;
    }

    public void execute(String itemId) {
        ViewingItemInputData viewItemInputData = new ViewingItemInputData(itemId);

        this.viewItemInteractor.execute(viewItemInputData);
    }
}
