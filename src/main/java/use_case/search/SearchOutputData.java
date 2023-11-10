package use_case.search;

import entities.Item;

public class SearchOutputData {
    public Item [] itemsFound;

    public SearchOutputData(Item [] itemsFound) {
        this.itemsFound = itemsFound;
    }

    public void setItemsFound(Item [] itemsFound) {
        this.itemsFound = itemsFound;
    }

    public Item [] getItemsFound() {
        return this.itemsFound;
    }
}
