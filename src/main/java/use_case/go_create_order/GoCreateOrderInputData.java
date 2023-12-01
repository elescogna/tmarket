package use_case.go_create_order;

import entities.Item;
import entities.Student;

public class GoCreateOrderInputData {
    private Item item;
    private Student user;

    public GoCreateOrderInputData(Student user, Item item) {
        this.user = user;
        this.item = item;
    }

    public Student getUser() { return user; }

    public Item getItem() { return item; }
}
