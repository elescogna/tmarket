package use_case.create_order;

import entities.Order;

import java.io.IOException;

public interface CreateOrderDataAccessInterfaceOrder {
    public Order createOrder (String buyerEmail, String sellerEmail, String itemId,
                        String address, String itemaName) throws IOException;
}
