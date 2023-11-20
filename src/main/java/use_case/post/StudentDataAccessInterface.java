package use_case.post;

import entities.Furniture;
import entities.Item;

public interface StudentDataAccessInterface {
    void addPostedItemToStudent(String uoftEmail, Item newItem);
}
