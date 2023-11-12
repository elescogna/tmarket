package interface_adapter.search;

import entities.Student;

import java.util.HashMap;

public class SearchState {
    private HashMap<String, String> filterableAttributes;
    private HashMap<String, String> filterChoices;
    private String filterableAttributesError;
    private String filterChoicesError;
    private Student currentStudent;

    public SearchState(SearchState copy) {
        this.filterableAttributes = copy.filterableAttributes;
        this.filterChoices = copy.filterChoices;
    }

    public SearchState() {}

    public HashMap<String, String> getFilterableAttributes() { return this.filterableAttributes; }

    public void setFilterableAttributes(HashMap<String, String> filterableAttributes) {
        this.filterableAttributes = filterableAttributes; }

    public HashMap<String, String> getFilterChoices() { return this.filterChoices; }

    public void setFilterChoices(HashMap<String, String> filterChoices) { this.filterChoices = filterChoices; }

    public String getFilterableAttributesError() {
        return filterableAttributesError;
    }

    public void setFilterableAttributesError(String filterableAttributesError) {
        this.filterableAttributesError = filterableAttributesError;
    }

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
