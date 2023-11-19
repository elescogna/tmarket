package interface_adapter.profile;

import entities.Item;
import entities.Order;

import java.util.ArrayList;

public class ProfileState {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private int contact;
    private ArrayList<Order> orders;
    private ArrayList<Item> postedItems;
    private String studentNotFoundError;


    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUoftEmail(String uoftEmail) {
        this.uoftEmail = uoftEmail;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public void setContact(int contact) {
        this.contact = contact;
    }
   public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }
    public void setStudentNotFoundError(String studentNotFoundError) {
        this.studentNotFoundError = studentNotFoundError;
    }
}
