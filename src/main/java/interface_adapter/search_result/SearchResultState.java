package interface_adapter.search_result;

import entities.Item;
import java.util.ArrayList;

public class SearchResultState {

    private ArrayList<Item> filteredItems = new ArrayList<>();
    private String filteredItemsError = "";

    public SearchResultState(SearchResultState copy) {
        this.filteredItems = copy.filteredItems;
        this.filteredItemsError = copy.filteredItemsError;
    }

    public SearchResultState() {}

    public ArrayList<Item> getFilteredItems() { return filteredItems; }

    public void setFilteredItems(ArrayList<Item> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public String getFilteredItemsError() { return filteredItemsError; }

    public void setFilteredItemsError(String filteredItemsError) {
        this.filteredItemsError = filteredItemsError;
    }
}
