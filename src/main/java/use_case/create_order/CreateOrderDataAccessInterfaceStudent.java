package use_case.create_order;

import entities.Order;

public interface CreateOrderDataAccessInterfaceStudent {
    public boolean existsByEmail(String email);
    public void updateOrders(String email, Order order);
}
