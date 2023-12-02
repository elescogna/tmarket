package interface_adapter.view_item;
import entities.Item;
import entities.Student;

public class ViewItemState {
    private Item currentItem;
    private String currentItemError;

    private Student currentStudent;

    public ViewItemState(ViewItemState copy) {
        this.currentItem = copy.currentItem;
        this.currentItemError = copy.currentItemError;
        this.currentStudent = copy.currentStudent;
    }

    public ViewItemState() {}

    public Item getCurrentItem() { return currentItem; }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public String getCurrentItemError() { return currentItemError; }

    public void setCurrentItemError(String currentItemError) {
        this.currentItemError = currentItemError;
    }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
