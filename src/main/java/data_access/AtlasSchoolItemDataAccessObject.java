package data_access;

import entities.Item;
import entities.SchoolItem;
import entities.Student;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.home.HomeDataAccessInterface;
import use_case.view_item.ViewItemDataAccessInterface;

public class AtlasSchoolItemDataAccessObject extends AtlasDataAccessObject
    implements HomeDataAccessInterface, ViewItemDataAccessInterface {
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
                String condition = itemDocument.getString("condition");
                double price = itemDocument.getDouble("price");
                int age = itemDocument.getInt("age");
                boolean soldYet = itemDocument.getBoolean("soldYet");
                String pickupAddress = itemDocument.getString("pickupAddress");
                // TODO: when we get around to this, we have to get a student based on
                // the owner ID that is provided here like:
                // studentDataAccessObject.get(jsonDocument.getString("ownerId"));
                Student owner = new Student("id", "test", "test", "test", "test", false,
                        new ArrayList<>());
                String type = itemDocument.getString("type");
                String picture = itemDocument.getString("picture");
                LocalDateTime creationTime =
                    LocalDateTime.parse(itemDocument.getString("creationTime"));

                // Item-specific attributes
                String brand = itemDocument.getString("brand");
                String colour = itemDocument.getString("colour");

                SchoolItem newItem = new SchoolItem(
                        id, name, description, condition, price, age, soldYet,
                        pickupAddress, owner, type, picture, creationTime, brand, colour);

                result.add(newItem);
            }

            return result;
        }
    }

    @Override
    public Item getItem(String idToGet) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);

        HashMap<String, String> filter = new HashMap<String, String>();
        filter.put("_id", idToGet);

        requestBodyMap.put("filter", filter);

        Request request = preparePostRequest(atlasCollectionName, "/action/findOne",
                requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseJson = new JSONObject(response.body().string());
            try {
                JSONObject itemDocument = responseJson.getJSONObject("document");
                // General item attributes

                String id = itemDocument.getString("_id");
                String name = itemDocument.getString("name");
                String description = itemDocument.getString("description");
                String condition = itemDocument.getString("condition");
                double price = itemDocument.getDouble("price");
                int age = itemDocument.getInt("age");
                boolean soldYet = itemDocument.getBoolean("soldYet");
                String pickupAddress = itemDocument.getString("pickupAddress");
                double radius = itemDocument.getDouble("radius");
                // TODO: when we get around to this, we have to get a student based on
                // the owner ID that is provided here like:
                // studentDataAccessObject.get(jsonDocument.getString("ownerId"));
                Student owner = new Student("id", "test", "test", "test", "test", false,
                        new ArrayList<>());
                String type = itemDocument.getString("type");
                String picture = itemDocument.getString("picture");
                LocalDateTime creationTime =
                    LocalDateTime.parse(itemDocument.getString("creationTime"));

                // Item-specific attributes
                String brand = itemDocument.getString("brand");
                String colour = itemDocument.getString("colour");

                SchoolItem newItem =
                    new SchoolItem(id, name, description, condition, price, age,
                            soldYet, pickupAddress, radius, owner, type, picture,
                            creationTime, brand, colour);

                return newItem;
            } catch (JSONException e) { // this means it's null
                return null;
            }
        }
    }
}
