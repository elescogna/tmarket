package interface_adapter.home;
import entities.Item;
import entities.Student;

import java.util.ArrayList;
public class HomeState {
    private Student user;
    private ArrayList<Item> wantedPosts = new ArrayList<>();
    public ArrayList<Item> getWantedPosts() { return this.wantedPosts; }
    public void setWantedPosts(ArrayList<Item> wantedPosts) {
        this.wantedPosts = wantedPosts;
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user) {
        this.user = user;
    }
}
