package use_case.create_order;

import java.io.IOException;

public interface CreateOrderDataAccessInterface {
    public String createOrder(String buyerEmail, String sellerEmail, String itemId,
                        String address, String itemName, String itemImageKey) throws IOException;
}
