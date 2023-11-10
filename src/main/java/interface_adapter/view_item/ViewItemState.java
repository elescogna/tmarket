package interface_adapter.view_item;
import entities.Item;

public class ViewItemState {
    private Item currentItem;
    private String currentItemError;

    public ViewItemState(ViewItemState copy) {
        this.currentItem = copy.currentItem;
        this.currentItemError = copy.currentItemError;
    }

    public ViewItemState() {}

    public Item getCurrentItem() { return currentItem; }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public String getCurrentItemError() { return currentItemError; }

    public void setCurrentItemError(String currentItemError) {
        this.currentItemError = currentItemError;
    }
}
