package use_case.view_order;

import entities.Order;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ViewOrderOutputData {
    Order orderToShow;
    ImageIcon orderItemImageIcon;
    String itemName;
    ArrayList<String> directions;

    public ViewOrderOutputData(Order orderToShow, String itemName,
            ImageIcon orderItemImageIcon,
            ArrayList<String> directions) {
        this.orderToShow = orderToShow;
        this.itemName = itemName;
        this.orderItemImageIcon = orderItemImageIcon;
        this.directions = directions;
    }

    public Order getOrderToShow() { return orderToShow; }

    public void setOrderToShow(Order orderToShow) {
        this.orderToShow = orderToShow;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    public ArrayList<String> getDirections() { return directions; }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }

    public ImageIcon getOrderItemImageIcon() { return orderItemImageIcon; }

    public void setOrderItemImageIcon(ImageIcon orderItemImageIcon) {
        this.orderItemImageIcon = orderItemImageIcon;
    }
}
