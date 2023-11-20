package use_case.post;

import entities.Furniture;
import entities.Technology;

import java.io.IOException;

public interface FurniturePostDataAccessInterface {

    void addItemToFurnitureCollection(Furniture furniture) throws IOException;
}
