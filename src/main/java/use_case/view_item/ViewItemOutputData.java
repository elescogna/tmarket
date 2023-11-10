package use_case.view_item;

import entities.Item;

public class ViewItemOutputData {
    Item itemToShow;

    public ViewItemOutputData(Item itemToShow) { this.itemToShow = itemToShow; }

    public Item getItemToShow() { return itemToShow; }

    public void setItemToShow(Item itemToShow) { this.itemToShow = itemToShow; }
}
