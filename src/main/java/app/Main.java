package app;

import data_access.AtlasItemDataAccessObject;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: remove, this is just POC
        AtlasItemDataAccessObject obj = new AtlasItemDataAccessObject();
        try {
            System.out.println(obj.getAllItems());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
