package data_access;

import entities.Furniture;
import entities.Item;
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

public class AtlasFurnitureDataAccessObject extends AtlasDataAccessObject
    implements HomeDataAccessInterface, ViewItemDataAccessInterface {
    private static final String atlasCollectionName = "furniture";

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
            if (response.code() != 200) {
                throw new IOException("Bad request made to Atlas Data API");
            }

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

                double length = itemDocument.getDouble("length");
                double width = itemDocument.getDouble("width");
                double height = itemDocument.getDouble("height");

                Furniture newItem =
                    new Furniture(id, name, description, condition, price, age, soldYet,
                            pickupAddress, owner, type, picture, creationTime,
                            length, width, height);

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
            // Student.get(jsonDocument.getString("ownerId"));
            Student owner = new Student("id", "test", "test", "test", "test", false,
                    new ArrayList<>());
            String type = itemDocument.getString("type");
            String picture = itemDocument.getString("picture");
            LocalDateTime creationTime =
                LocalDateTime.parse(itemDocument.getString("creationTime"));

            // Item-specific attributes

            double length = itemDocument.getDouble("length");
            double width = itemDocument.getDouble("width");
            double height = itemDocument.getDouble("height");

            Furniture newItem = new Furniture(
                    id, name, description, condition, price, age, soldYet, pickupAddress,
                    radius, owner, type, picture, creationTime, length, width, height);

            return newItem;
        }
    }
}
