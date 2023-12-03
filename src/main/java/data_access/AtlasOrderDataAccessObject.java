package data_access;

import entities.Item;
import entities.Order;
import entities.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.create_order.CreateOrderDataAccessInterface;
import use_case.profile.ProfileDataAccessInterface;
import use_case.view_order.ViewOrderDataAccessInterface;

public class AtlasOrderDataAccessObject extends AtlasDataAccessObject
    implements CreateOrderDataAccessInterface, ViewOrderDataAccessInterface,
               ProfileDataAccessInterface {

    private static final String atlasCollectionName = "orders";

    public String createOrder(String buyerEmail, String sellerEmail,
            String itemId, String address, String itemName,
            String itemImageKey) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, Object> documentValue = new HashMap<String, Object>();

        documentValue.put("buyerEmail", buyerEmail);
        documentValue.put("sellerEmail", sellerEmail);
        documentValue.put("itemId", itemId);
        documentValue.put("pickupLocation", address);
        documentValue.put("itemName", itemName);
        documentValue.put("itemImageKey", itemImageKey);

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
    public ArrayList<Order> getAllOrdersBySellerEmail(String sellerEmail)
        throws IOException {
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
                String itemImageKey = itemDocument.getString("itemImageKey");

                Order newOrder = new Order(id, buyerEmail, retrievedSellerEmail, itemId,
                        pickupLocation, itemName, itemImageKey);

                result.add(newOrder);
            }

            return result;
        }
    }

    public Student getStudentByEmail(String id) throws IOException {
        return null;
    }

    @Override
    public ArrayList<Item> getAllItemsByOwnerID(String ownerId)
        throws IOException {
        return null;
    }

    @Override
    public Order getOrder(String idToGet) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);

        HashMap<String, Object> filter = new HashMap<String, Object>();

        // getting something by ID takes a bit more work
        HashMap<String, String> idMap = new HashMap<String, String>();
        idMap.put("$oid", idToGet);

        filter.put("_id", idMap);
        requestBodyMap.put("filter", filter);

        Request request = preparePostRequest(atlasCollectionName, "/action/findOne",
                requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Bad request made to Atlas Data API");
            }

            JSONObject responseJson = new JSONObject(response.body().string());

            if (responseJson.isNull("document")) {
                return null;
            }

            JSONObject orderDocument = responseJson.getJSONObject("document");
            // General item attributes

            String id = orderDocument.getString("_id");
            String buyerEmail = orderDocument.getString("buyerEmail");
            String sellerEmail = orderDocument.getString("sellerEmail");
            String itemName = orderDocument.getString("itemName");
            String pickupLocation = orderDocument.getString("pickupLocation");
            String itemId = orderDocument.getString("itemId");
            String itemImageKey = orderDocument.getString("itemImageKey");

            // Order-specific attributes

            Order newOrder = new Order(id, buyerEmail, sellerEmail, itemId,
                    pickupLocation, itemName, itemImageKey);

            return newOrder;
        }
    }

    @Override
    public ArrayList<String> getDirections(String currentStudentAddress,
            String pickupAddress)
        throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        final String API_KEY = System.getenv("GOOGLE_MAPS_API_KEY");
        String url = "https://maps.googleapis.com/maps/api/directions/json?";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        httpBuilder.addQueryParameter("origin", currentStudentAddress);
        httpBuilder.addQueryParameter("destination", pickupAddress);
        httpBuilder.addQueryParameter("key", API_KEY);

        Request request = new Request.Builder()
            .url(httpBuilder.build().toString())
            .method("GET", null)
            .addHeader("Content-Type", "application/json")
            .build();

        ArrayList<String> results = new ArrayList<String>();

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONObject suggestedRoute =
                (JSONObject)responseBodyJson.getJSONArray("routes").get(0);
            JSONArray suggestedRoutesLegs = suggestedRoute.getJSONArray("legs");

            for (Object leg : suggestedRoutesLegs) {
                JSONObject legJsonObj = (JSONObject)leg;
                JSONArray steps = legJsonObj.getJSONArray("steps");

                for (Object step : steps) {
                    JSONObject stepJsonObj = (JSONObject)step;

                    String distance =
                        stepJsonObj.getJSONObject("distance").getString("text");
                    String instructions = stepJsonObj.getString("html_instructions")
                        .replaceAll("</b>|<b>", "")
                        .replaceAll("</div>", ".")
                        .replaceAll("<div.*>", ". ");

                    results.add(String.format("(%s) %s", distance, instructions));
                }
            }
        }

        return results;
    }
}
