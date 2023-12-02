package interface_adapter.view_order;

import entities.Order;
import java.util.ArrayList;

public class ViewOrderState {
    private Order currentOrder;
    private String currentOrderError;
    private String currentItemNameToShow;
    private ArrayList<String> currentDirections;

    public ViewOrderState(ViewOrderState copy) {
        this.currentOrder = copy.currentOrder;
        this.currentOrderError = copy.currentOrderError;
        this.currentItemNameToShow = copy.currentItemNameToShow;
        this.currentDirections = copy.currentDirections;
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

    public ArrayList<String> getCurrentDirections() { return currentDirections; }

    public void setCurrentDirections(ArrayList<String> currentDirections) {
        this.currentDirections = currentDirections;
    }
}
