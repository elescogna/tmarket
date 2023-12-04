package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {

    private String viewName;

    /**
     * General constructor for ViewModel. Creates the ViewModel with the given view name.
     *
     * @param viewName the view name with which to create the view model.
     */
    public ViewModel(String viewName) { this.viewName = viewName; }
    public String getViewName() { return this.viewName; }

    public abstract void firePropertyChanged();
    public abstract void
        addPropertyChangeListener(PropertyChangeListener listener);
}
