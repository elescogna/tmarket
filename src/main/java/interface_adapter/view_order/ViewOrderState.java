package interface_adapter.view_order;

import entities.Order;
import entities.Student;

public class ViewOrderState {
    private Order currentOrder;
    private String currentOrderError;
    private String currentItemNameToShow;

    public ViewOrderState(Order currentOrder, String currentOrderError,
            String currentItemNameToShow, Student currentStudent) {
        this.currentOrder = currentOrder;
        this.currentOrderError = currentOrderError;
        this.currentItemNameToShow = currentItemNameToShow;
    }

    public ViewOrderState() {}

    public Order getCurrentOrder() { return currentOrder; }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getCurrentOrderError() { return currentOrderError; }

    public void setCurrentOrderError(String currentOrderError) {
        this.currentOrderError = currentOrderError;
    }

    public String getCurrentItemNameToShow() { return currentItemNameToShow; }

    public void setCurrentItemNameToShow(String currentItemNameToShow) {
        this.currentItemNameToShow = currentItemNameToShow;
    }
}
