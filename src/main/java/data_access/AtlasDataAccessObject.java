package data_access;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.*;
import org.json.JSONArray;
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

    protected double calculateDistance (String source, String destination) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        final String API_KEY = System.getenv("GOOGLE_MAPS_API_KEY");
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("destinations", destination);
        httpBuilder.addQueryParameter("origins", source);
        httpBuilder.addQueryParameter("key", API_KEY);

        Request request =
                new Request.Builder()
                        .url(httpBuilder.build().toString())
                        .method("GET", null)
                        .addHeader("Content-Type", "application/json")
                        .build();

        double distanceValue = 0;
        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONArray rows = responseBodyJson.getJSONArray("rows");

            for (Object row : rows) {
                JSONObject rowItem = (JSONObject) row;
                JSONArray elements = rowItem.getJSONArray("elements");
                for (Object element : elements) {
                    JSONObject elementItem = (JSONObject) element;
                    JSONObject distanceInformation = elementItem.getJSONObject("distance");
                    String distance = distanceInformation.getString("text"); // string value in miles
                    distanceValue = distanceInformation.getInt("value");
                }
            }

        }
        return distanceValue;
    }
}
