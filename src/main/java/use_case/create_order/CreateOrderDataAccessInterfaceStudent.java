package use_case.create_order;

import java.io.IOException;

import entities.Order;

public interface CreateOrderDataAccessInterfaceStudent {
    public boolean existsByEmail(String email) throws IOException;
}
