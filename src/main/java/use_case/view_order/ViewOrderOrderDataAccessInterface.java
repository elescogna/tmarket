package use_case.view_order;

import entities.Order;
import java.io.IOException;

public interface ViewOrderOrderDataAccessInterface {
    Order getOrder(String idToGet) throws IOException;
}
