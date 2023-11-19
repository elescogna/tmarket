package use_case.viewing_item;

public class ViewingItemInputData {
    String itemId;

    public ViewingItemInputData(String itemId) { this.itemId = itemId; }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }
}
