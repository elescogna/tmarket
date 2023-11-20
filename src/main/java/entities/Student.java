package entities;

import java.util.*;

public class Student {
    private String id;
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private boolean verified;
    private ArrayList<Item> postedItems;
    private ArrayList<Order> orders;
    private int contact;

    public Student(String id, String name, String password, String homeAddress,
                   String uoftEmail, ArrayList<Item> postedItems, ArrayList<Order> orders) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.homeAddress = homeAddress;
        this.uoftEmail = uoftEmail;
        this.postedItems = postedItems;
        this.orders = orders;
    }
    public Student(String name, String password, String homeAddress,
                   String uoftEmail, ArrayList<Item> postedItems, ArrayList<Order> orders) {
        this.name = name;
        this.password = password;
        this.homeAddress = homeAddress;
        this.uoftEmail = uoftEmail;
        this.postedItems = postedItems;
        this.orders = orders;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

    public void setUoftEmail(String uoftEmail) { this.uoftEmail = uoftEmail; }

    public String getUoftEmail() { return uoftEmail; }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() { return homeAddress; }

    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }

    public ArrayList<Item> getPostedItems() { return postedItems; }
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() { return orders; }
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
