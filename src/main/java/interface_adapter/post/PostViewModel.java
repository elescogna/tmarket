package interface_adapter.post;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostViewModel extends ViewModel {
    private PostState state = new PostState();

    /**
     * Default constructor of PostViewModel.
     */
    public PostViewModel() {
        super("post");
    }

    public PostState getState() {
        return this.state;
    }

    public void setState(PostState currentState) {
        this.state = currentState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("postState", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
