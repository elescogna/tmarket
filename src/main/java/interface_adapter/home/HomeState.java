package interface_adapter.home;
import entities.Item;
import java.util.ArrayList;
public class HomeState {
    private ArrayList<Item> wantedPosts = new ArrayList<>();
    public ArrayList<Item> getWantedPosts() { return this.wantedPosts; }
    public void setWantedPosts(ArrayList<Item> wantedPosts) {
        this.wantedPosts = wantedPosts;
    }
}
