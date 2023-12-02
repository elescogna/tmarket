package use_case.view_order;

import entities.Student;

public class ViewOrderInputData {
    private String orderId;

    public ViewOrderInputData(String orderId, Student currentStudent) {
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
}
