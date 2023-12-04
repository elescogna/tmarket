package use_case.view_item;

import entities.Student;

public class ViewItemInputData {
    private String itemId;
    private Student currentStudent;

    /**
     * Creates a new ViewItemInputData with the given parameters.
     *
     * @param itemId the item ID with which to create the ViewItemInputData
     * @param currentStudent the currentStudent with which to create the ViewItemInputData
     */
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
