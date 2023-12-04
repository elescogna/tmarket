package interface_adapter.view_item;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewItemViewModel extends ViewModel {
    private ViewItemState state = new ViewItemState();

    /**
     * Default constructor for ViewItemViewModel.
     */
    public ViewItemViewModel() { super("view_item"); }

    public void setState(ViewItemState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("viewItemState", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewItemState getState() { return state; }
}
