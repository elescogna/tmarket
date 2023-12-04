package interface_adapter.view_order;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewOrderViewModel extends ViewModel {
    private ViewOrderState state = new ViewOrderState();

    /**
     * Default constructor for ViewOrderViewModel
     */
    public ViewOrderViewModel() { super("view_order"); }

    public void setState(ViewOrderState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("viewOrderState", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewOrderState getState() { return state; }
}
