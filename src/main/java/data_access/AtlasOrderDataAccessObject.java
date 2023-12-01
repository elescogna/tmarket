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

  public Order createOrder(String buyerEmail, String sellerEmail, String itemId,
                      String address) throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
    HashMap<String, Object> documentValue = new HashMap<String, Object>();

    documentValue.put("buyerEmail", buyerEmail);
    documentValue.put("sellerEmail", sellerEmail);
    documentValue.put("itemId", itemId);
    documentValue.put("pickupLocation", address);

    requestBodyMap.put("dataSource", atlasDataSourceName);
    requestBodyMap.put("database", atlasDatabaseName);
    requestBodyMap.put("collection", atlasCollectionName);
    requestBodyMap.put("document", documentValue);

    Request request = preparePostRequest(atlasCollectionName,
                                         "/action/insertOne", requestBodyMap);

    Response response = client.newCall(request).execute();
    JSONObject responseBodyJson = new JSONObject(response.body().string());
    String id = responseBodyJson.getString("insertedId");
    return new Order(id, buyerEmail, sellerEmail, itemId, address);
  }
}
