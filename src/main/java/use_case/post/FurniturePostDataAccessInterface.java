package use_case.post;

import entities.Furniture;

import java.io.IOException;

public interface FurniturePostDataAccessInterface {
    void addItemToFurnitureCollection(Furniture furniture) throws IOException;
    int getLastImageIndex() throws IOException;
}
