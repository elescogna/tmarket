package use_case.view_item;

import entities.Item;
import entities.Student;
import javax.swing.ImageIcon;

public class ViewItemOutputData {
    Item itemToShow;
    ImageIcon itemImageIcon;
    Student currentStudent;

    public ViewItemOutputData(Item itemToShow, ImageIcon itemImageIcon,
            Student currentStudent) {
        this.itemToShow = itemToShow;
        this.itemImageIcon = itemImageIcon;
        this.currentStudent = currentStudent;
    }

    public Item getItemToShow() { return itemToShow; }

    public void setItemToShow(Item itemToShow) { this.itemToShow = itemToShow; }

    public Student getCurrentStudent() { return currentStudent; }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public ImageIcon getItemImageIcon() { return itemImageIcon; }

    public void setItemImageIcon(ImageIcon itemImageIcon) {
        this.itemImageIcon = itemImageIcon;
    }
}
