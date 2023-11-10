package app;

import data_access.AtlasTechnologyDataAccessObject;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: remove, this is just POC
        AtlasTechnologyDataAccessObject obj = new AtlasTechnologyDataAccessObject();
        try {
            System.out.println(obj.getItem("654dcde2bb63415367a0e75e"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
