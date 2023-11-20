package data_access;

import entities.Item;
import entities.SchoolItem;
import entities.Student;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Technology;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.home.HomeDataAccessInterface;
import use_case.post.SchoolItemPostDataAccessInterface;

public class AtlasSchoolItemDataAccessObject
    extends AtlasDataAccessObject implements HomeDataAccessInterface, SchoolItemPostDataAccessInterface {
    private static final String atlasCollectionName = "school-items";

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
                int condition = itemDocument.getInt("condition");
                int price = itemDocument.getInt("price");
                int age = itemDocument.getInt("age");
                boolean soldYet = itemDocument.getBoolean("soldYet");
                String pickupAddress = itemDocument.getString("pickupAddress");
                double radius = itemDocument.getDouble("radius");
                // TODO: when we get around to this, we have to get a student based on
                // the owner ID that is provided here like:
                // studentDataAccessObject.get(jsonDocument.getString("ownerId"));
                Student owner = new Student("id", "test", "test", "test", "test",
                        new ArrayList<>(), new ArrayList<>());
                String type = itemDocument.getString("type");
                String picture = itemDocument.getString("picture");
                LocalDateTime creationTime =
                    LocalDateTime.parse(itemDocument.getString("creationTime"));

                // Item-specific attributes
                String brand = itemDocument.getString("brand");
                String colour = itemDocument.getString("colour");

                SchoolItem newItem =
                    new SchoolItem(name, description, condition, price, age,
                            soldYet, pickupAddress, owner, type, picture,
                            creationTime, brand, colour);

                result.add(newItem);
            }

            return result;
        }
    }

    private HashMap<String, Object> itemToDocument(SchoolItem item) {
        HashMap<String, Object> document = new HashMap<>();
        document.put("name", item.getName());
        document.put("description", item.getDescription());
        document.put("condition", item.getCondition());
        document.put("price", item.getPrice());
        document.put("age", item.getAge());
        document.put("soldYet", item.isSoldYet());
        document.put("pickupAddress", item.getPickupAddress());
        document.put("ownerId", item.getOwner().getId()); // You might need to change this based on how the owner is identified in your system
        document.put("type", item.getType());
        document.put("picture", item.getPicture());
        document.put("creationTime", item.getCreationTime().toString());
        document.put("brand", item.getBrand());
        document.put("colour", item.getColour());

        return document;
    }

    public void addItemToSchoolItemCollection(SchoolItem newItem) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("document", itemToDocument(newItem));

        Request request = preparePostRequest(atlasCollectionName, "/action/insert", requestBodyMap);


        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Handle a successful response
                System.out.println("Item added successfully to the collection!");
            }

            else {
                // Handle an unsuccessful response
                System.out.println("Failed to add item to the collection. HTTP status code: " + response.code());
                // You might want to log more details or throw an exception based on your requirements
            }
        }
    }
}
