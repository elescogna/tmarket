package use_case.view_order;

import entities.Order;

import java.io.IOException;

public interface ViewOrderDataAccessInterface {
    Order getOrder(String idToGet) throws IOException;
}
