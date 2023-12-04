package interface_adapter.search_result;

import entities.Item;
import entities.Student;

import java.util.ArrayList;

public class SearchResultState {

    private ArrayList<Item> filteredItems = new ArrayList<>();
    private Student currentStudent;


    /**
     * Default empty constructor for SearchResultState
     */
    public SearchResultState() {}

    public ArrayList<Item> getFilteredItems() { return filteredItems; }

    public void setFilteredItems(ArrayList<Item> filteredItems) { this.filteredItems = filteredItems; }

    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
    }

    public Student getCurrentStudent(){
        return currentStudent;
    }
}
