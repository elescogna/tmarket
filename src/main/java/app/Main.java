package app;

import data_access.AtlasTechnologyDataAccessObject;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: remove, this is just POC
        AtlasTechnologyDataAccessObject obj = new AtlasTechnologyDataAccessObject();
        try {
            obj.getItem("634dcde2bb63415367a0e75e");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
