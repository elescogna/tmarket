package use_case.create_order;

import entities.Item;

public interface CreateOrderDataAccessInterfaceOrder {
    public void create(String orderId, String buyerEmail, String sellerEmail, Item item,
        String address);
}
