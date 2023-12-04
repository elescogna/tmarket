package interface_adapter.search_result;

import entities.Item;
import entities.Student;

import java.util.ArrayList;

public class SearchResultState {

    private ArrayList<Item> filteredItems = new ArrayList<>();
    private String filteredItemsError = "";
    private Student currentStudent;

    /**
     * Constructor for the SearchResultState that makes a new state from a copy.
     *
     * @param copy A SearchResultState from which to make this SearchResultState.
     */
    public SearchResultState(SearchResultState copy) {
        this.filteredItems = copy.filteredItems;
        this.filteredItemsError = copy.filteredItemsError;
    }

    /**
     * Default empty constructor for SearchResultState
     */
    public SearchResultState() {}

    public ArrayList<Item> getFilteredItems() { return filteredItems; }

    public void setFilteredItems(ArrayList<Item> filteredItems) { this.filteredItems = filteredItems; }

    public String getFilteredItemsError() { return filteredItemsError; }

    public void setFilteredItemsError(String filteredItemsError) { this.filteredItemsError = filteredItemsError; }

    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
    }

    public Student getCurrentStudent(){
        return currentStudent;
    }
}
