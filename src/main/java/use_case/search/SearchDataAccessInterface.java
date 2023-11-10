package use_case.search;

import entities.Item;

import java.io.IOException;

public interface SearchDataAccessInterface {
    Item[] getItemsByFilters(String [] filteredAttributes) throws IOException;
}
