package data_access;
import entities.Item;
import entities.Order;
import entities.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.contact.ContactDataAccessInterface;
import use_case.create_order.CreateOrderDataAccessInterfaceStudent;
import use_case.login.LoginDataAccessInterface;
import use_case.profile.ProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class AtlasStudentDataAccessObject extends AtlasDataAccessObject
    implements ProfileDataAccessInterface, SignupUserDataAccessInterface,
               CreateOrderDataAccessInterfaceStudent, LoginDataAccessInterface,
               ContactDataAccessInterface {
    private static final String atlasCollectionName = "students";

    public boolean existsByEmail(String email) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
        HashMap<String, String> filterValue = new HashMap<String, String>();
        filterValue.put("uoftEmail", email);

        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("filter", filterValue);

        Request request = preparePostRequest(atlasCollectionName, "/action/findOne",
                requestBodyMap);

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Bad request made to Atlas Data API");
            }

            JSONObject responseBodyJson = new JSONObject(response.body().string());

            if (responseBodyJson.isNull("document")) {
                return false;
            }

            return true;
        }
    }

    public boolean checkPassword(String uoftEmail, String password)
            throws IOException {
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            HashMap<String, Object> requestBodyMap = new HashMap<String, Object>();
            HashMap<String, String> filterValue = new HashMap<String, String>();
            filterValue.put("uoftEmail", uoftEmail);
            filterValue.put("password", password);

            requestBodyMap.put("dataSource", atlasDataSourceName);
            requestBodyMap.put("database", atlasDatabaseName);
            requestBodyMap.put("collection", atlasCollectionName);
            requestBodyMap.put("filter", filterValue);

            Request request = preparePostRequest(atlasCollectionName, "/action/findOne",
                    requestBodyMap);

            try (Response response = client.newCall(request).execute()) {
                if (response.code() != 200) {
                    throw new IOException("Bad request made to Atlas Data API");
                }

                JSONObject responseBodyJson = new JSONObject(response.body().string());

                if (responseBodyJson.isNull("document")) {
                    return false;
                }

                return true;
            }
    }

    // haven't used it, kept in case someone else needs it. If you are using it,
    // remove this comment.
    // If comment still exists in the end, will delete this method. Use at your
    // own risk.
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

                Student student =
                    new Student(id, name, password, homeAddress, uoftEmail);

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
            JSONArray orders = studentDocument.getJSONArray("orders");
            ArrayList<Order> arrayOrders = new ArrayList<>();
            for (int i = 0; i < orders.length(); i++) {
                Object element = orders.get(i);
                arrayOrders.add((Order)element);
            }
            JSONArray postedItems = studentDocument.getJSONArray("postedItems");
            ArrayList<Item> arrayPosted = new ArrayList<>();
            for (int i = 0; i < postedItems.length(); i++) {
                Object element = postedItems.get(i);
                arrayPosted.add((Item)element);
            }

            return new Student(id, name, password, homeAddress, uoftEmail);
        }
    }
    public Student getStudentByEmail(String email) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        HashMap<String, String> filter = new HashMap<>();
        filter.put("uoftEmail", email);

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
                // Student with the given email not found
                return null;
            }

            JSONObject studentDocument = studentDocuments.getJSONObject(0);
            String id = studentDocument.getString("_id");
            String name = studentDocument.getString("name");
            String password = studentDocument.getString("password");
            String uoftEmail = studentDocument.getString("uoftEmail");
            String homeAddress = studentDocument.getString("homeAddress");

            return new Student(id, name, password, homeAddress, uoftEmail);
        }
    }

    private HashMap<String, Object> itemToDocument(Student student) {
        HashMap<String, Object> document = new HashMap<>();

        document.put("name", student.getName());
        document.put("password", student.getPassword());
        document.put("uoftEmail", student.getUoftEmail());
        document.put("homeAddress", student.getHomeAddress());

        return document;
    }
    public void addStudent(Student newStudent) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HashMap<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("dataSource", atlasDataSourceName);
        requestBodyMap.put("database", atlasDatabaseName);
        requestBodyMap.put("collection", atlasCollectionName);
        requestBodyMap.put("document", itemToDocument(newStudent));

        Request request = AtlasStudentDataAccessObject.preparePostRequest(
                atlasCollectionName, "/action/insertOne", requestBodyMap);

        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Handle a successful response
                System.out.println("Item added successfully to the collection!");
            } else {
                // Handle an unsuccessful response
                System.out.println(
                        "Failed to add item to the collection. HTTP status code: " +
                        response.code());
                // You might want to log more details or throw an exception based on
                // your requirements
            }
        }
    }
}
