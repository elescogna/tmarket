package interface_adapter.home;
import entities.Item;
import entities.Student;
import java.util.ArrayList;

public class HomeState {
    private Student currentStudent;

    private ArrayList<Item> allPosts = new ArrayList<>();

    public ArrayList<Item> getAllPosts() { return this.allPosts; }

    public void setAllPosts(ArrayList<Item> wantedPosts) {
        this.allPosts = wantedPosts;
    }

    public Student getStudent() { return currentStudent; }

    public void setCurrentStudent(Student user) { this.currentStudent = user; }
}
