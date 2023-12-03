package interface_adapter.view_item;
import entities.Item;
import entities.Student;
import javax.swing.ImageIcon;

public class ViewItemState {
    private Item currentItem;
    private ImageIcon currentItemImage;
    private String currentItemError;

    private Student currentStudent;

    public ViewItemState(ViewItemState copy) {
        this.currentItem = copy.currentItem;
        this.currentItemImage = copy.currentItemImage;
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

    public ImageIcon getCurrentItemImage() { return currentItemImage; }

    public void setCurrentItemImage(ImageIcon currentItemImage) {
        this.currentItemImage = currentItemImage;
    }
}
