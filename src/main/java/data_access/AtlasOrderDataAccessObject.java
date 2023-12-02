package data_access;
import entities.Item;
import entities.Order;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Student;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.create_order.CreateOrderDataAccessInterfaceOrder;
import use_case.profile.ProfileDataAccessInterface;

public class AtlasOrderDataAccessObject extends AtlasDataAccessObject
        implements CreateOrderDataAccessInterfaceOrder, ProfileDataAccessInterface {

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

    requestBodyMap.put("dataSource", atlasDataSourceName);
    requestBodyMap.put("database", atlasDatabaseName);
    requestBodyMap.put("collection", atlasCollectionName);
    requestBodyMap.put("document", documentValue);

    Request request = preparePostRequest(atlasCollectionName,
            "/action/insertOne", requestBodyMap);

    try (okhttp3.Response response = client.newCall(request).execute()) {
      if (response.isSuccessful()) {
        // Handle a successful response
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

  @Override
  public ArrayList<Order> getAllOrdersBySellerEmail(String sellerEmail) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

    HashMap<String, String> filter = new HashMap<>();
    filter.put("sellerEmail", sellerEmail);

    requestBodyMap.put("dataSource", atlasDataSourceName);
    requestBodyMap.put("database", atlasDatabaseName);
    requestBodyMap.put("collection", atlasCollectionName);
    requestBodyMap.put("filter", filter);

    Request request =
            preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

    try (Response response = client.newCall(request).execute()) {
      if (response.code() != 200) {
        throw new IOException("Bad request made to Atlas Data API");
      }

      JSONObject responseBodyJson = new JSONObject(response.body().string());
      JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");

      ArrayList<Order> result = new ArrayList<Order>();

      for (Object document : allItemDocuments) {
        JSONObject itemDocument = (JSONObject)document;

        // General item attributes

        String id = itemDocument.getString("_id");
        String buyerEmail = itemDocument.getString("buyerEmail");
        String retrievedSellerEmail = itemDocument.getString("sellerEmail");
        String itemId = itemDocument.getString("itemId");
        String pickupLocation = itemDocument.getString("pickupLocation");
        String itemName = itemDocument.getString("itemName");

        Order newOrder = new Order(id, buyerEmail, retrievedSellerEmail, itemId, pickupLocation, itemName);

        result.add(newOrder);
      }

      return result;
    }
  }

  public Student getStudentByEmail(String id) throws IOException {
    return null;
  }

  @Override
  public ArrayList<Item> getAllItemsByOwnerID(String ownerId) throws IOException {
    return null;
  }
}