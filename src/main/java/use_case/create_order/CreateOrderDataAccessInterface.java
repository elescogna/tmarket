package use_case.create_order;

import entities.Item;

import java.io.IOException;

public interface CreateOrderDataAccessInterface {
    public boolean existsByEmail(String email);

    public void create(String orderId, String buyerEmail, String sellerEmail, Item item,
        String address);

    public void update(String itemId);
}
