package use_case.create_order;

import entities.Item;
import entities.Order;

import java.io.IOException;

public interface CreateOrderDataAccessInterfaceOrder {
    public Order create (String buyerEmail, String sellerEmail, Item item,
                        String address) throws IOException;
}
