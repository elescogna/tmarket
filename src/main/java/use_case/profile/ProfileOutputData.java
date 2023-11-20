package use_case.profile;

import entities.Item;
import entities.Order;

import java.util.ArrayList;

public class ProfileOutputData {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private ArrayList<Order> orders;
    private ArrayList<Item> postedItems;

    public ProfileOutputData(String name, String password, String uoftEmail, String homeAddress,
                             ArrayList<Order> orders, ArrayList<Item> postedItems){
        this.name = name;
        this.password = password;
        this.uoftEmail = uoftEmail;
        this.homeAddress = homeAddress;
        this.orders = orders;
        this.postedItems = postedItems;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getUoftEmail() {
        return uoftEmail;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public ArrayList<Item> getPostedItems() {
        return postedItems;
    }
}