package use_case.home;

import entities.Item;
import java.util.ArrayList;

public class HomeOutputData {
    private ArrayList<Item> wantedPosts;
    /**
     * Creates a new HomeOutputData with the given posts.
     *
     * @param wantedPosts the posts with which to create the HomeOutputData
     */
    public HomeOutputData(ArrayList<Item> wantedPosts) {
        this.wantedPosts = wantedPosts;
    }
    public ArrayList<Item> getAllPosts() { return wantedPosts; }
}
