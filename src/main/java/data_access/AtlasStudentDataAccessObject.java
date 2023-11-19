package data_access;

import entities.Item;
import entities.Order;
import entities.Student;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.profile.ProfileDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AtlasStudentDataAccessObject extends AtlasDataAccessObject implements ProfileDataAccessInterface {
    private static final String atlasCollectionName = "students";

    // haven't used it, kept in case someone else needs it. If you are using it, remove this comment.
    // If comment still exists in the end, will delete this method. Use at your own risk.
    public ArrayList<Student> getAllStudents() throws IOException {
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

            ArrayList<Student> result = new ArrayList<Student>();

            for (Object document : allItemDocuments) {
                JSONObject itemDocument = (JSONObject)document;

                // General item attributes

                String id = itemDocument.getString("id");
                String name = itemDocument.getString("name");
                String password = itemDocument.getString("password");
                String uoftEmail = itemDocument.getString("uoftEmail");
                String homeAddress = itemDocument.getString("homeAddress");
                int contact = itemDocument.getInt("contact");
                JSONArray orders = itemDocument.getJSONArray("orders");
                ArrayList<Order> arrayOrders = new ArrayList<>();
                for (int i = 0; i < orders.length(); i++) {
                    Object element = orders.get(i);
                    arrayOrders.add((Order) element);
                }
                JSONArray postedItems = itemDocument.getJSONArray("postedItems");
                ArrayList<Item> arrayPosted = new ArrayList<>();
                for (int i = 0; i < postedItems.length(); i++) {
                    Object element = postedItems.get(i);
                    arrayPosted.add((Item) element);
                }
                boolean verified = itemDocument.getBoolean("verified");

                Student student = new Student(id, name, password, homeAddress, uoftEmail, verified, arrayPosted, arrayOrders, contact);

                result.add(student);
            }

            return result;
        }
    }

    public Student getStudentById(String studentId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        HashMap<String, String> filter = new HashMap<>();
        filter.put("id", studentId);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filter);

        Request request =
                preparePostRequest(atlasCollectionName, "/action/find", requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBodyJson = new JSONObject(response.body().string());
            JSONArray studentDocuments = responseBodyJson.getJSONArray("documents");

            if (studentDocuments.length() == 0) {
                // Student with the given ID not found
                return null;
            }

            JSONObject studentDocument = studentDocuments.getJSONObject(0);

            String id = studentDocument.getString("id");
            String name = studentDocument.getString("name");
            String password = studentDocument.getString("password");
            String uoftEmail = studentDocument.getString("uoftEmail");
            String homeAddress = studentDocument.getString("homeAddress");
            int contact = studentDocument.getInt("contact");
            JSONArray orders = studentDocument.getJSONArray("orders");
            ArrayList<Order> arrayOrders = new ArrayList<>();
            for (int i = 0; i < orders.length(); i++) {
                Object element = orders.get(i);
                arrayOrders.add((Order) element);
            }
            JSONArray postedItems = studentDocument.getJSONArray("postedItems");
            ArrayList<Item> arrayPosted = new ArrayList<>();
            for (int i = 0; i < postedItems.length(); i++) {
                Object element = postedItems.get(i);
                arrayPosted.add((Item) element);
            }
            boolean verified = studentDocument.getBoolean("verified");

            return new Student(id, name, password, homeAddress, uoftEmail, verified, arrayPosted, arrayOrders, contact);
        }
    }


}

