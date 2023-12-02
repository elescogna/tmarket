package use_case.view_order;

public class ViewOrderInputData {
    private String orderId;
    private String currentStudentEmail;

    public ViewOrderInputData(String orderId, String currentStudentEmail) {
        this.orderId = orderId;
        this.currentStudentEmail = currentStudentEmail;
    }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCurrentStudentEmail() { return currentStudentEmail; }

    public void setCurrentStudentEmail(String currentStudentEmail) {
        this.currentStudentEmail = currentStudentEmail;
    }
}
