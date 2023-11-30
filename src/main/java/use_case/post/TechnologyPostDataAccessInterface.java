package use_case.post;

import entities.Student;
import entities.Technology;

import java.io.IOException;

public interface TechnologyPostDataAccessInterface {
    void addItemToTechnologyCollection(Technology newTechnology) throws IOException;
}
