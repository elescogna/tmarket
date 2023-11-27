package interface_adapter.search_result;

import entities.Student;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchResultViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search results";
    private SearchResultState state = new SearchResultState();
    private Student currentStudent;

    public SearchResultViewModel() {
        super("search_result");
    }

    public void setState(SearchResultState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchResultState getState() {
        return state;
    }


    public Student getCurrentStudent() {
        return this.currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
