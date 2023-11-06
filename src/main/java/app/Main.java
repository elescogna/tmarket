package app;

import data_access.AtlasClothingDataAccessObject;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: remove, this is just POC
        AtlasClothingDataAccessObject obj = new AtlasClothingDataAccessObject();
        try {
            System.out.println(obj.getAllItems());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
