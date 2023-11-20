package interface_adapter.posting;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostingViewModel extends ViewModel {
    private PostingState state = new PostingState();
    public PostingViewModel(){super ("use_case/posting");}

    public void setState(PostingState state) {
        this.state = state;
    }

    public PostingState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("postingState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
