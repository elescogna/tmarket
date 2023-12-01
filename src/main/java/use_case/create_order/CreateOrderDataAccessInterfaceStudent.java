package use_case.create_order;

import entities.Order;
import java.io.IOException;

public interface CreateOrderDataAccessInterfaceStudent {
    public boolean existsByEmail(String email) throws IOException;
    public void updateOrders(String email, Order order);
}
