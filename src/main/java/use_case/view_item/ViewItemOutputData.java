package use_case.view_item;

import entities.Item;
import entities.Student;

public class ViewItemOutputData {
    Item itemToShow;
    Student currentStudent;

    public ViewItemOutputData(Item itemToShow, Student currentStudent) {
        this.itemToShow = itemToShow;
        this.currentStudent = currentStudent;
    }
    public Item getItemToShow() { return itemToShow; }

    public void setItemToShow(Item itemToShow) { this.itemToShow = itemToShow; }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
