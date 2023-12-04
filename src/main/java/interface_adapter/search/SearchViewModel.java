package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    private SearchState state = new SearchState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Default constructor for SearchViewModel.
     */
    public SearchViewModel() { super("search"); }

    public SearchState getState() { return state; }

    public void setState(SearchState state) { this.state = state; }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("searchState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
