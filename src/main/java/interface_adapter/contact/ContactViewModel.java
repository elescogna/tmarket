package interface_adapter.contact;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ContactViewModel extends ViewModel {
    private ContactState state = new ContactState();

    /**
     * Default constructor for ContactViewModel.
     */
    public ContactViewModel() { super("contact"); }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("contactState", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}

    public ContactState getState() { return state; }

    public void setState(ContactState state) { this.state = state; }
}
