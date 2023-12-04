package interface_adapter.search;

import entities.Student;

import java.util.HashMap;

public class SearchState {
    private HashMap<String, Object> filterChoices;
    private String filterChoicesError;
    private Student currentStudent;

    /**
     * Default constructor for search state.
     */
    public SearchState() {}

    public HashMap<String, Object> getFilterChoices() { return this.filterChoices; }

    public void setFilterChoices(HashMap<String, Object> filterChoices) { this.filterChoices = filterChoices; }


    public String getFilterChoicesError() {
        return filterChoicesError;
    }

    public void setFilterChoicesError(String filterChoicesError) {
        this.filterChoicesError = filterChoicesError;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
