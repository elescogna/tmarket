package use_case.profile;

import entities.Item;
import entities.Order;
import entities.Student;

import java.util.ArrayList;

public class ProfileOutputData {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private ArrayList<Item> allItems;
    private ArrayList<Order> allOrders;
    private Student currentStudent;
    public ProfileOutputData(String name, String password, String uoftEmail, String homeAddress,
                             ArrayList<Item> items, ArrayList<Order> allOrders,
                             Student currentStudent){
        this.name = name;
        this.password = password;
        this.uoftEmail = uoftEmail;
        this.homeAddress = homeAddress;
        this.allItems = items;
        this.allOrders = allOrders;
        this.currentStudent = currentStudent;
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

    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }
    public Student getCurrentStudent() { return this.currentStudent; }
}