package interface_adapter.home;
import entities.Item;
import entities.Student;
import java.util.ArrayList;

public class HomeState {
    private Student user;
    private ArrayList<Item> allPosts = new ArrayList<>();
    public ArrayList<Item> getAllPosts() { return this.allPosts; }
    public void setAllPosts(ArrayList<Item> wantedPosts) {
        this.allPosts = wantedPosts;
    }

    public Student getUser() { return user; }

    public void setUser(Student user) { this.user = user; }
}
