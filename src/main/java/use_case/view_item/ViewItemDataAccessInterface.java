package use_case.view_item;

import java.io.IOException;

import entities.Item;

public interface ViewItemDataAccessInterface {
    Item getItem(String id) throws IOException;
}
