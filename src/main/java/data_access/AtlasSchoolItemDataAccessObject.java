package data_access;

import entities.Furniture;
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
import use_case.create_order.CreateOrderDataAccessInterfaceItem;
import use_case.home.HomeDataAccessInterface;
import use_case.post.SchoolItemPostDataAccessInterface;
import use_case.search.SearchDataAccessInterface;
import use_case.view_item.ViewItemDataAccessInterface;

public class AtlasSchoolItemDataAccessObject
    extends AtlasDataAccessObject implements HomeDataAccessInterface, SchoolItemPostDataAccessInterface, CreateOrderDataAccessInterfaceItem, ViewItemDataAccessInterface, SearchDataAccessInterface {

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
                int condition = itemDocument.getInt("condition");
                int price = itemDocument.getInt("price");
                int age = itemDocument.getInt("age");
                boolean soldYet = itemDocument.getBoolean("soldYet");
                String pickupAddress = itemDocument.getString("pickupAddress");
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
        } catch (IOException e) {}
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
                    id, name, description, condition, price, age, soldYet, pickupAddress,
                    owner, type, picture, creationTime, brand, colour);

            return newItem;
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

        requestBodyMap.put("filter", newFilteredAttributes);

        // sort by creation time
        requestBodyMap.put("sort", new HashMap<String, Object>() {{
            put("creationTime", 1); // 1 for ascending, -1 for descending
        }});

        Request request = preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

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

                // This line assumes that calculateDistance is implemented
                // and that we have access to the current user infomation
                double distance = calculateDistance(currentStudent.getHomeAddress(), pickupAddress);
                double maxDistance = Double.parseDouble((String)filteredAttributes.get("distanceRange"));

                if (distance < maxDistance) {
                    SchoolItem newItem =
                            new SchoolItem(id, name, description, condition, price, age,
                                    soldYet, pickupAddress, owner, type, picture,
                                    creationTime, brand, colour);

                    result.add(newItem);
                }
            }

            return result;
        }
    }
}
