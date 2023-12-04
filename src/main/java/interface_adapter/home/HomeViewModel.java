package interface_adapter.home;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {
    private HomeState state = new HomeState();

    /**
     * Default constructor for the HomeViewModel
     */
    public HomeViewModel() { super("home"); }

    public void setState(HomeState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("homeState", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public HomeState getState() { return state; }
}
