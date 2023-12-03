package interface_adapter.view_order;

import entities.Order;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ViewOrderState {
    private Order currentOrder;
    private String currentOrderError;
    private String currentItemNameToShow;
    private ImageIcon currentOrderItemImage;
    private ArrayList<String> currentDirections;

    public ViewOrderState(ViewOrderState copy) {
        this.currentOrder = copy.currentOrder;
        this.currentOrderError = copy.currentOrderError;
        this.currentItemNameToShow = copy.currentItemNameToShow;
        this.currentOrderItemImage = copy.currentOrderItemImage;
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

    public ImageIcon getCurrentOrderItemImage() {
        return currentOrderItemImage;
    }

    public void setCurrentOrderItemImage(ImageIcon currentOrderItemImageIcon) {
        this.currentOrderItemImage = currentOrderItemImageIcon;
    }

}
