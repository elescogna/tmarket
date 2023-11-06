package use_case.home;

import java.util.ArrayList;

public class HomeOutputData {
    private ArrayList<String> wantedPosts;
    public HomeOutputData(ArrayList<String> wantedPosts){
        this.wantedPosts = wantedPosts;
    }
    public ArrayList<String> getWantedPosts() {
        return wantedPosts;
    }
}