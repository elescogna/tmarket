package interface_adapter.profile;

import entities.Item;
import entities.Order;
import java.util.ArrayList;

public class ProfileState {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Item> postedItems = new ArrayList<>();

    private String studentNotFoundError;

    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setUoftEmail(String uoftEmail) { this.uoftEmail = uoftEmail; }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public void setOrders(ArrayList<Order> orders) { this.orders = orders; }
    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }
    public void setStudentNotFoundError(String studentNotFoundError) {
        this.studentNotFoundError = studentNotFoundError;
    }

    public String getName() { return name; }
    public String getUoftEmail() { return uoftEmail; }

    public ArrayList<Item> getPostedItems() { return postedItems; }

    public ArrayList<Order> getOrders() { return orders; }
    public String getPassword() { return password; }
    public String getHomeAddress() { return homeAddress; }
    public String getStudentNotFoundError() { return studentNotFoundError; }
}
