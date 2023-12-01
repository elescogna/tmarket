package interface_adapter.profile;

import entities.Item;
import entities.Order;
import java.util.ArrayList;

public class ProfileState {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private ArrayList<Order> orders;
    private ArrayList<Item> postedItems;
    private String studentNotFoundError;

    public ProfileState(ProfileState copy) {
        this.name = copy.name;
        this.password = copy.password;
        this.uoftEmail = copy.uoftEmail;
        this.homeAddress = copy.homeAddress;
        this.orders = copy.orders;
        this.postedItems = copy.postedItems;
        this.studentNotFoundError = copy.studentNotFoundError;
    }

    public ProfileState() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUoftEmail() { return uoftEmail; }

    public void setUoftEmail(String uoftEmail) { this.uoftEmail = uoftEmail; }

    public String getHomeAddress() { return homeAddress; }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public ArrayList<Order> getOrders() { return orders; }

    public void setOrders(ArrayList<Order> orders) { this.orders = orders; }

    public ArrayList<Item> getPostedItems() { return postedItems; }

    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }

    public String getStudentNotFoundError() { return studentNotFoundError; }

    public void setStudentNotFoundError(String studentNotFoundError) {
        this.studentNotFoundError = studentNotFoundError;
    }
}
