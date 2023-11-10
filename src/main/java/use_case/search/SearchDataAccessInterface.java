package use_case.search;

import entities.Item;
import entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface SearchDataAccessInterface {
    ArrayList<Item> getItemsByFilters(HashMap<String, String> filteredAttributes, Student currentStudent) throws IOException;
}
