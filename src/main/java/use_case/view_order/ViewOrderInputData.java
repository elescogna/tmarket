package use_case.view_order;

public class ViewOrderInputData {
    private String orderId;

    public ViewOrderInputData(String orderId) { this.orderId = orderId; }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
}
