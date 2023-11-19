package use_case.view_item;

import entities.Item;
import java.io.IOException;

public interface ViewItemDataAccessInterface {
    Item getItem(String idToGet) throws IOException;
}
