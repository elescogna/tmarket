package use_case.go_create_order;

import entities.Item;
import entities.Student;

public class GoCreateOrderOutputData {
    private Student user;
    private Item item;

    public GoCreateOrderOutputData(Student user, Item item) {
        this.user = user;
        this.item = item;
    }

    public Student getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }
}
