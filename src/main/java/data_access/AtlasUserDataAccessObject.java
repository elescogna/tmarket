package data_access;

import java.io.IOException;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import use_case.HomeDataAccessInterface;

public class AtlasUserDataAccessObject implements HomeDataAccessInterface {
    private static final String atlasDataSourceName =
        System.getenv("ATLAS_DATA_SOURCE_NAME");
    private static final String atlasDatabaseName =
        System.getenv("ATLAS_DATABASE_NAME");
    private static final String atlasCollectionName = "users";
    private static final String atlasApiEndpoint =
        System.getenv("ATLAS_API_ENDPOINT");
    private static final String atlasApiKey = System.getenv("ATLAS_API_KEY");

    @Override
    public String getAllItems() throws IOException {
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
            return response.body().string();
        }
    }
}
