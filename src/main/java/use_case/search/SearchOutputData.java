package use_case.search;

import entities.Item;
import entities.Student;

import java.util.ArrayList;

public class SearchOutputData {
    public ArrayList<Item> itemsFound;
    private Student currentStudent;

    /**
     * Creates a new SearchOutputData with the parameters given.
     *
     * @param itemsFound the found items with which to make the SearchOutputData
     * @param currentStudent the student with whcih to make the SearchOutputData
     */
    public SearchOutputData(ArrayList<Item> itemsFound, Student currentStudent) { this.itemsFound = itemsFound;
    this.currentStudent = currentStudent;}

    public ArrayList<Item>  getItemsFound() {
        return itemsFound;
    }
    public Student getStudent(){return currentStudent;}
}
