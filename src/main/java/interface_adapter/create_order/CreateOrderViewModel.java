package interface_adapter.create_order;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateOrderViewModel extends ViewModel {

    private CreateOrderState state = new CreateOrderState();

    public CreateOrderViewModel() {
        super("create order");
    }

    public void setState(CreateOrderState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateOrderState getState() {
        return state;
    }
}

