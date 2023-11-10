package use_case.view_item;

import entities.Student;

public class ViewItemInputData {
    String itemId;
    Student currentStudent;

    public ViewItemInputData(String itemId, Student currentStudent) {
        this.itemId = itemId;
        this.currentStudent = currentStudent;
    }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
