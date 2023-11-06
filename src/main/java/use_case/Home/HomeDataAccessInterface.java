package use_case.Home;

import entities.Item;
import java.io.IOException;
import java.util.ArrayList;

public interface HomeDataAccessInterface {
    ArrayList<Item> getAllItems() throws IOException;
}
