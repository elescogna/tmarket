package use_case.post;

import java.io.IOException;

import entities.Furniture;
import entities.Item;

public interface StudentPostDataAccessInterface {
    void addPostedItemToStudent(String uoftEmail, Item newItem) throws IOException;
}
