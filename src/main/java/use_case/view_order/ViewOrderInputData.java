package use_case.view_order;

public class ViewOrderInputData {
    private String orderId;
    private String currentStudentEmail;
    private String currentStudentAddress;

    public ViewOrderInputData(String orderId, String currentStudentEmail,
            String currentStudentAddress) {
        this.orderId = orderId;
        this.currentStudentEmail = currentStudentEmail;
        this.currentStudentAddress = currentStudentAddress;
    }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCurrentStudentEmail() { return currentStudentEmail; }

    public void setCurrentStudentEmail(String currentStudentEmail) {
        this.currentStudentEmail = currentStudentEmail;
    }

    public String getCurrentStudentAddress() { return currentStudentAddress; }

    public void setCurrentStudentAddress(String currentStudentAddress) {
        this.currentStudentAddress = currentStudentAddress;
    }
}
