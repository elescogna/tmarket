package data_access;

import entities.Item;
import entities.Student;
import entities.Technology;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.HomeDataAccessInterface;

public class AtlasTechnologyDataAccessObject
    implements HomeDataAccessInterface {
    private static final String atlasDataSourceName =
        System.getenv("ATLAS_DATA_SOURCE_NAME");
    private static final String atlasDatabaseName =
        System.getenv("ATLAS_DATABASE_NAME");
    private static final String atlasCollectionName = "technology";
    private static final String atlasApiEndpoint =
        System.getenv("ATLAS_API_ENDPOINT");
    private static final String atlasApiKey = System.getenv("ATLAS_API_KEY");

    @Override
    public ArrayList<Item> getAllItems() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", new HashMap<String, String>());

        RequestBody requestBody =
            RequestBody.create(new JSONObject(requestBodyMap).toString(),
                    MediaType.parse("application/json; charset=utf-8"));

        Request request =
            new Request.Builder()
            .url(atlasApiEndpoint + "/action/find")
            .method("POST", requestBody)
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Accept", "application/json; charset=utf-8")
            .addHeader("apiKey", atlasApiKey)
            .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");

            ArrayList<Item> result = new ArrayList<Item>();

            for (Object document : allItemDocuments) {
                JSONObject itemDocument = (JSONObject)document;

                // General item attributes

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
                Student owner = new Student("test", "test", "test", "test", false,
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
                    new Technology(name, description, condition, price, age, soldYet,
                            pickupAddress, radius, owner, type, picture,
                            creationTime, brand, capabilities, colour);

                result.add(newItem);
            }

            return result;
        }
    }
}
