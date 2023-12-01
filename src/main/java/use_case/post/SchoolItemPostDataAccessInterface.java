package use_case.post;

import entities.SchoolItem;
import java.io.IOException;

public interface SchoolItemPostDataAccessInterface {
    void addItemToSchoolItemCollection(SchoolItem newSchoolItem)
            throws IOException;
}
