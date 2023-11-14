package use_case.home;

import entities.Item;
import use_case.DataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public interface HomeDataAccessInterface extends DataAccessInterface {
    ArrayList<Item> getAllItems() throws IOException;
}
