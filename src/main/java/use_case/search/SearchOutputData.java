package use_case.search;

import entities.Item;

import java.util.ArrayList;

public class SearchOutputData {
    public ArrayList<Item> itemsFound;

    public SearchOutputData(ArrayList<Item> itemsFound) { this.itemsFound = itemsFound; }

    public void setItemsFound(ArrayList<Item> itemsFound) {
        this.itemsFound = itemsFound;
    }

    public ArrayList<Item>  getItemsFound() {
        return this.itemsFound;
    }
}
