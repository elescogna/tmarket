package app;

import data_access.AtlasFurnitureDataAccessObject;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: remove, this is just POC
        AtlasFurnitureDataAccessObject obj = new AtlasFurnitureDataAccessObject();
        try {
            System.out.println(obj.getAllFurniture());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
