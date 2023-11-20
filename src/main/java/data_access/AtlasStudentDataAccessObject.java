package data_access;

import entities.Order;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.create_order.CreateOrderDataAccessInterfaceStudent;
import use_case.login.LoginDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AtlasStudentDataAccessObject extends AtlasDataAccessObject implements CreateOrderDataAccessInterfaceStudent, LoginDataAccessInterface {
    private static final String atlasCollectionName = "students";

    public boolean existsByEmail(String email) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        boolean exists = true;

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, String> filterValue = new HashMap<String, String>();
        filterValue.put("uoftEmail", email);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filterValue);

        Request request =
                preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            try {
                JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");
            } catch (JSONException j) {
                exists = false;
            }
        } catch (IOException e) {
            System.out.println("Cannot check whether the student exists or not.");
        }
        return exists;
    }

    public boolean existsByPassword(String password) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        boolean exists = true;

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, String> filterValue = new HashMap<String, String>();
        filterValue.put("password", password);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filterValue);

        Request request =
                preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            try {
                JSONArray allItemDocuments = responseBodyJson.getJSONArray("documents");
            } catch (JSONException j) {
                exists = false;
            }
        } catch (IOException e) {
            System.out.println("Cannot check whether the student exists or not.");
        }
        return exists;
    }

    public void updateOrders(String email, Order order) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        HashMap<String, String> filter = new HashMap<>();
        filter.put("uoftEmail", email);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filter);

        Request request =
                preparePostRequest(atlasCollectionName, "/action/findOne", requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONObject document = responseBodyJson.getJSONObject("document");
            JSONArray ordersJson = document.getJSONArray("orders");
            ArrayList<Order> orders = new ArrayList<Order>();
            for (Object o: ordersJson) {
                orders.add((Order)o);
            }
            orders.add(order);
            requestBodyMap = new HashMap<String, Object>();
            HashMap<String, String> filterValue = new HashMap<String, String>();
            filterValue.put("uoftEmail", email);
            HashMap<String, ArrayList<Order>> newValue = new HashMap<String, ArrayList<Order>>();
            newValue.put("orders", orders);
            HashMap<String, HashMap<String, ArrayList<Order>>> updateValue = new HashMap<String, HashMap<String, ArrayList<Order>>>();
            updateValue.put("$set", newValue);

            requestBodyMap.put("dataSource", atlasDataSourceName);
            requestBodyMap.put("database", atlasDatabaseName);
            requestBodyMap.put("collection", atlasCollectionName);
            requestBodyMap.put("filter", filterValue);
            requestBodyMap.put("update", updateValue);

            Request secondRequest = preparePostRequest(atlasCollectionName, "/action/updateOne", requestBodyMap);
            client.newCall(secondRequest).execute();
        } catch (IOException e) {
            System.out.println("Unable to get student orders!");
        }
    }
}
