package use_case.view_order;

import entities.Item;
import java.io.IOException;

public interface ViewOrderItemDataAccessInterface {
    Item getItem(String idToGet) throws IOException;
}
