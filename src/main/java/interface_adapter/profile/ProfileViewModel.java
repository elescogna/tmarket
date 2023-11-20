package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.home.HomeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private ProfileState state = new ProfileState();
    public ProfileViewModel() {
        super("use_case/profile");
    }
    public void setState(ProfileState profileState) { this.state = profileState; }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("profileState", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ProfileState getState() {

        return state;
    }

}
