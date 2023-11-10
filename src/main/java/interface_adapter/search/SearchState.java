package interface_adapter.search;

import entities.Item;

import java.util.ArrayList;

public class SearchState {
    private ArrayList<Item> currentItems;
    private String currentItemError;

    public SearchState(SearchState copy) {
        this.currentItems = copy.currentItems;
        this.currentItemError = copy.currentItemError;
    }

    public SearchState() {}

    public ArrayList<Item> getCurrentItem() { return currentItems; }

    public void setCurrentItem(ArrayList<Item> currentItem) {
        this.currentItems = currentItem;
    }

    public String getCurrentItemError() { return currentItemError; }

    public void setCurrentItemError(String currentItemError) {
        this.currentItemError = currentItemError;
    }
}
