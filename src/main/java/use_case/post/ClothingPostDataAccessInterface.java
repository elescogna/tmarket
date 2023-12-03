package use_case.post;

import entities.Clothing;

import java.io.IOException;

public interface ClothingPostDataAccessInterface {
    void addItemToClothingCollection(Clothing newClothing) throws IOException;
    int getLastImageIndex() throws IOException;
}
