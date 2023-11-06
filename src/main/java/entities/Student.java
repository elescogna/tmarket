package entities;
import java.util.*;

public class Student {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;
    private boolean verified;
    private ArrayList<Item> postedItems;

    public Student(String name, String password, String uoftEmail,
            String homeAddress, boolean verified,
            ArrayList<Item> postedItems) {
        this.name = name;
        this.password = password;
        this.uoftEmail = uoftEmail;
        this.homeAddress = homeAddress;
        this.verified = verified;
        this.postedItems = postedItems;
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

    public void setVerified(boolean verified) { this.verified = verified; }

    public boolean getVerified() { return verified; }

    public void setPostedItems(ArrayList<Item> postedItems) {
        this.postedItems = postedItems;
    }

    public ArrayList<Item> getPostedItems() { return postedItems; }
}
