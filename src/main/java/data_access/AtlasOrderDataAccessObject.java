package data_access;
import entities.Item;
import entities.Order;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import use_case.create_order.CreateOrderDataAccessInterfaceOrder;

public class AtlasOrderDataAccessObject extends AtlasDataAccessObject
    implements CreateOrderDataAccessInterfaceOrder {

  private static final String atlasCollectionName = "orders";

  public String createOrder(String buyerEmail, String sellerEmail, String itemId,
                      String address, String itemName) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
    HashMap<String, Object> documentValue = new HashMap<String, Object>();

    documentValue.put("buyerEmail", buyerEmail);
    documentValue.put("sellerEmail", sellerEmail);
    documentValue.put("itemId", itemId);
    documentValue.put("pickupLocation", address);
    documentValue.put("itemName", itemName);

    System.out.println("document value: " + documentValue);

    requestBodyMap.put("dataSource", atlasDataSourceName);
    requestBodyMap.put("database", atlasDatabaseName);
    requestBodyMap.put("collection", atlasCollectionName);
    requestBodyMap.put("document", documentValue);

    System.out.println("request body: " + requestBodyMap);

    Request request = preparePostRequest(atlasCollectionName,
                                         "/action/insertOne", requestBodyMap);

    try (okhttp3.Response response = client.newCall(request).execute()) {
      if (response.isSuccessful()) {
        // Handle a successful response
        System.out.println("reponse: " + response);
        System.out.println("Inserted ID" + itemId);
        System.out.println("Item added successfully to the collection!");
        return itemId;
      } else {
        // Handle an unsuccessful response
        System.out.println(
                "Failed to add item to the collection. HTTP status code: " +
                        response.code());
        // You might want to log more details or throw an exception based on
        // your requirements
      }
    }
    return null;
  }
}
