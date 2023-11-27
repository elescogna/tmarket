package use_case.profile;

import entities.Item;
import entities.Order;

import java.util.ArrayList;

public class ProfileOutputData {
    private String name;
    private String password;
    private String uoftEmail;
    private String homeAddress;

    public ProfileOutputData(String name, String password, String uoftEmail, String homeAddress){
        this.name = name;
        this.password = password;
        this.uoftEmail = uoftEmail;
        this.homeAddress = homeAddress;
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
}
