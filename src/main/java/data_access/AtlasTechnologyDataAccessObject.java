package data_access;

import entities.Item;
import entities.Student;
import entities.Technology;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.create_order.CreateOrderDataAccessInterfaceItem;
import use_case.home.HomeDataAccessInterface;

public class AtlasTechnologyDataAccessObject
    extends AtlasDataAccessObject implements HomeDataAccessInterface, CreateOrderDataAccessInterfaceItem {
    private static final String atlasCollectionName = "technology";

    @Override
    public ArrayList<Item> getAllItems() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", new HashMap<String, String>());

        Request request =
            preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");

            ArrayList<Item> result = new ArrayList<Item>();

            for (Object document : allItemDocuments) {
                JSONObject itemDocument = (JSONObject)document;

                // General item attributes

                String id = itemDocument.getString("_id");
                String name = itemDocument.getString("name");
                String description = itemDocument.getString("description");
                String condition = itemDocument.getString("condition");
                double price = itemDocument.getDouble("price");
                int age = itemDocument.getInt("age");
                boolean soldYet = itemDocument.getBoolean("soldYet");
                String pickupAddress = itemDocument.getString("pickupAddress");
                // TODO: when we get around to this, we have to get a student based on
                // the owner ID that is provided here like:
                // Student.get(jsonDocument.getString("ownerId"));
                Student owner = new Student("id", "test", "test", "test", "test", false,
                        new ArrayList<>());
                String type = itemDocument.getString("type");
                String picture = itemDocument.getString("picture");
                LocalDateTime creationTime =
                    LocalDateTime.parse(itemDocument.getString("creationTime"));

                // Item-specific attributes

                String brand = itemDocument.getString("brand");
                String colour = itemDocument.getString("colour");
                ArrayList<String> capabilities = new ArrayList<>();

                for (Object capability :
                        itemDocument.getJSONArray("capabilities").toList()) {
                    capabilities.add((String)capability);
                        }

                Technology newItem =
                    new Technology(id, name, description, condition, price, age,
                            soldYet, pickupAddress, owner, type, picture,
                            creationTime, brand, capabilities, colour);

                result.add(newItem);
            }

            return result;
        }
    }

    public void updateSoldYet(String itemId) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, String> filterValue = new HashMap<String, String>();
        filterValue.put("_id", itemId);
        HashMap<String, Boolean> newValue = new HashMap<String, Boolean>();
        newValue.put("soldYet", true);
        HashMap<String, HashMap<String, Boolean>> updateValue = new HashMap<String, HashMap<String, Boolean>>();
        updateValue.put("$set", newValue);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filterValue);
        requestBodyMap.put("update", updateValue);

        Request request = preparePostRequest(atlasCollectionName, "/action/updateOne", requestBodyMap);

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("Attribute not updated!");
        }
    }
}
