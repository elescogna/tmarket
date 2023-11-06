package use_case.home;

import java.util.ArrayList;

public class HomeOutputData {
    private ArrayList<Item> wantedPosts;
    public HomeOutputData(ArrayList<Item> wantedPosts){
        this.wantedPosts = wantedPosts;
    }
    public ArrayList<Item> getWantedPosts() {
        return wantedPosts;
    }
}