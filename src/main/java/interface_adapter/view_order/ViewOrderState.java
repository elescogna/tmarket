package interface_adapter.view_order;

import entities.Order;
import entities.Student;

public class ViewOrderState {
    private Order currentOrder;
    private String currentOrderError;

    private Student currentStudent;

    public ViewOrderState(ViewOrderState copy) {
        this.currentOrder = copy.currentOrder;
        this.currentOrderError = copy.currentOrderError;
        this.currentStudent = copy.currentStudent;
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

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
