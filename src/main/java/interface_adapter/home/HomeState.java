package interface_adapter.home;
import java.util.ArrayList;
public class HomeState {
    private ArrayList<String> wantedPosts = new ArrayList<>();
    public ArrayList<String> getWantedPosts() {
        return this.wantedPosts;
    }
    public void setWantedPosts(ArrayList<String> wantedPosts) {
        this.wantedPosts = wantedPosts;
    }
}
