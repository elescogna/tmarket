package interface_adapter.home;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.ViewModel;

public class HomeViewModel extends ViewModel{
    private HomeState state = new HomeState();

    public HomeViewModel() {
        super("use_case/home");
    }

    public void setState(HomeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("homeState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomeState getState() {
        return state;
    }
}
