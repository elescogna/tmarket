package use_case.view_order;

import entities.Order;

public class ViewOrderOutputData {
    Order itemToShow;

    public ViewOrderOutputData(Order itemToShow) { this.itemToShow = itemToShow; }

    public Order getItemToShow() { return itemToShow; }

    public void setItemToShow(Order itemToShow) { this.itemToShow = itemToShow; }
}
