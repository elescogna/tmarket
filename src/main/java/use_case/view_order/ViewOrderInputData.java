package use_case.view_order;

import entities.Student;

public class ViewOrderInputData {
    private String orderId;
    private Student currentStudent;

    public ViewOrderInputData(String orderId, Student currentStudent) {
        this.orderId = orderId;
        this.currentStudent = currentStudent;
    }

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
