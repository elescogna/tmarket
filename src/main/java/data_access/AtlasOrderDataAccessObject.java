package data_access;

import entities.Item;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import use_case.create_order.CreateOrderDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;

public class AtlasOrderDataAccessObject extends AtlasDataAccessObject implements CreateOrderDataAccessInterface {
    private static final String atlasCollectionName = "orders";

    public boolean existsByEmail(String email) {return true;}

    public void create(String orderId, String buyerEmail, String sellerEmail, Item item,
                       String address) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, Object> documentValue = new HashMap<String, Object>();

        documentValue.put("_id", orderId);
        documentValue.put("buyerEmail", buyerEmail);
        documentValue.put("sellerEmail", sellerEmail);
        documentValue.put("item", item);
        documentValue.put("pickupLocation", address);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("document", documentValue);

        Request request = preparePostRequest(atlasCollectionName, "/action/insertOne", requestBodyMap);

        try {
            client.newCall(request).execute();
        } catch (IOException e) {}
    }

    public void update(String itemId) {}
}
