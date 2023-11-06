package data_access;

import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

public class AtlasDataAccessObject {
    protected static final String atlasDataSourceName =
        System.getenv("ATLAS_DATA_SOURCE_NAME");
    protected static final String atlasDatabaseName =
        System.getenv("ATLAS_DATABASE_NAME");
    protected static final String atlasApiUrl =
        System.getenv("ATLAS_API_ENDPOINT");
    protected static final String atlasApiKey = System.getenv("ATLAS_API_KEY");

    protected static Request
        preparePostRequest(String atlasCollectionName, String endpoint,
                HashMap<String, Object> requestBodyMap) {
            RequestBody requestBody =
                RequestBody.create(new JSONObject(requestBodyMap).toString(),
                        MediaType.parse("application/json; charset=utf-8"));

            Request request =
                new Request.Builder()
                .url(atlasApiUrl + endpoint)
                .method("POST", requestBody)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Accept", "application/json; charset=utf-8")
                .addHeader("apiKey", atlasApiKey)
                .build();

            return request;
        }
}
