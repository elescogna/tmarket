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
import use_case.search.SearchDataAccessInterface;

public class AtlasFurnitureDataAccessObject extends AtlasDataAccessObject
        implements HomeDataAccessInterface, SearchDataAccessInterface {
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
            if (responseBodyJson.isNull("document")) {
                return null;
            }

            JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");

            ArrayList<Item> result = new ArrayList<Item>();

            for (Object document : allItemDocuments) {
                JSONObject itemDocument = (JSONObject) document;

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
                                pickupAddress, owner, type, picture,
                                creationTime, length, width, height);

                result.add(newItem);
            }

            return result;
        }
    }

    @Override
    public ArrayList<Item> getItemsByFilters(HashMap<String, Object> filteredAttributes, Student currentStudent)
            throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);

        // create a deep copy so that you don't mutate the parameter
        HashMap<String, Object> newFilteredAttributes = new HashMap<>(filteredAttributes.size());
        for (HashMap.Entry<String, Object> entry : filteredAttributes.entrySet()) {
            newFilteredAttributes.put(new String(entry.getKey()), new String(String.valueOf(entry.getValue())));
        }

        // Now modify all the attributes that need a range to account for a range instead of a single exact value
        HashMap<String, Object> priceRangeMap = new HashMap<>();
        priceRangeMap.put("$lte", newFilteredAttributes.get("price"));
        newFilteredAttributes.put("price", priceRangeMap);

        HashMap<String, Object> ageMap = new HashMap<>();
        ageMap.put("$lte", newFilteredAttributes.get("age"));
        newFilteredAttributes.put("age", ageMap);

        HashMap<String, Object> conditionScoreMap = new HashMap<>();
        conditionScoreMap.put("$gte", newFilteredAttributes.get("conditionScore"));
        newFilteredAttributes.put("conditionScore", conditionScoreMap);

        // Filter for soldYet
        newFilteredAttributes.put("soldYet", false);

        // Furniture-specific filters
        HashMap<String, Object> lengthRangeMap = new HashMap<>();
        lengthRangeMap.put("$lte", newFilteredAttributes.get("maxLength"));
        lengthRangeMap.put("$gte", newFilteredAttributes.get("minLength"));
        newFilteredAttributes.put("length", lengthRangeMap);

        HashMap<String, Object> widthRangeMap = new HashMap<>();
        widthRangeMap.put("$lte", newFilteredAttributes.get("maxWidth"));
        widthRangeMap.put("$gte", newFilteredAttributes.get("minWidth"));
        newFilteredAttributes.put("width", widthRangeMap);

        HashMap<String, Object> heightRangeMap = new HashMap<>();
        heightRangeMap.put("$lte", newFilteredAttributes.get("maxHeight"));
        heightRangeMap.put("$gte", newFilteredAttributes.get("minHeight"));
        newFilteredAttributes.put("height", heightRangeMap);

        requestBodyMap.put("filter", newFilteredAttributes);

        // sort by creation time
        requestBodyMap.put("sort", new HashMap<String, Object>() {{
            put("creationTime", 1); // 1 for ascending, -1 for descending
        }});

        Request request = preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

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

                double length = itemDocument.getDouble("length");
                double width = itemDocument.getDouble("width");
                double height = itemDocument.getDouble("height");

                // This line assumes that calculateDistance is implemented
                // and that we have access to the current user infomation
                double distance = calculateDistance(currentStudent.getHomeAddress(), pickupAddress);
                double maxDistance = Double.parseDouble((String)filteredAttributes.get("distanceRange"));

                if (distance <= maxDistance) {
                    Furniture newItem =
                            new Furniture(id, name, description, condition, price, age, soldYet,
                                    pickupAddress, owner, type, picture,
                                    creationTime, length, width, height);

                    result.add(newItem);
                }
            }
            return result;
        }
    }
}