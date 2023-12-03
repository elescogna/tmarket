package use_case.post;

import entities.Technology;

import java.io.IOException;

public interface TechnologyPostDataAccessInterface {
    void addItemToTechnologyCollection(Technology newTechnology) throws IOException;
    int getLastImageIndex() throws IOException;
}
