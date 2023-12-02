package use_case.view_order;

import entities.Order;
import entities.Student;

public class ViewOrderOutputData {
    Order orderToShow;
    Student currentStudent;

    public ViewOrderOutputData(Order orderToShow, Student currentStudent) {
        this.orderToShow = orderToShow;
        this.currentStudent = currentStudent;
    }

    public Order getOrderToShow() { return orderToShow; }

    public void setOrderToShow(Order orderToShow) { this.orderToShow = orderToShow; }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
