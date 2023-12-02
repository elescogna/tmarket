package use_case.view_order;

import entities.Order;
import entities.Student;

public class ViewOrderOutputData {
    Order orderToShow;
    String itemName;

    public ViewOrderOutputData(Order orderToShow, String itemName) {
        this.orderToShow = orderToShow;
        this.itemName = itemName;
    }

    public Order getOrderToShow() { return orderToShow; }

    public void setOrderToShow(Order orderToShow) {
        this.orderToShow = orderToShow;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }
}
