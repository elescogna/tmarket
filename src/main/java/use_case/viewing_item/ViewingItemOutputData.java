package use_case.viewing_item;

public class ViewingItemOutputData {
    String itemId;

    public ViewingItemOutputData(String itemId) { this.itemId = itemId; }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }
}
