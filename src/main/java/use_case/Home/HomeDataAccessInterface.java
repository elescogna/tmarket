package use_case.Home;

import java.io.IOException;
import java.util.ArrayList;

import entities.Item;

public interface HomeDataAccessInterface {
    ArrayList<Item> getAllItems() throws IOException;
}
