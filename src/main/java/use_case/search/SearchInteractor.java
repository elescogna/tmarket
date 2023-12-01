package use_case.search;

import entities.Item;
import entities.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchInteractor implements SearchInputBoundary {
    final SearchDataAccessInterface clothingDataAccessObject;
    final SearchDataAccessInterface furnitureDataAccessObject;
    final SearchDataAccessInterface schoolItemDataAccessObject;
    final SearchDataAccessInterface technologyDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface clothingDataAccessObject,
            SearchDataAccessInterface furnitureDataAccessObject,
            SearchDataAccessInterface schoolItemDataAccessObject,
            SearchDataAccessInterface technologyDataAccessObject,
            SearchOutputBoundary searchOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.searchPresenter = searchOutputBoundary;
    }
    @Override
    public void execute(SearchInputData searchInputData) {
        try {
            ArrayList<Item> itemsFound;
            HashMap<String, Object> filteredAttributes =
                searchInputData.getFilteredAttributes();
            Student currentStudent = searchInputData.getCurrentStudent();

            // TODO: this does not seem like the best CA practice but I am not sure
            // what else to do
            Object category = filteredAttributes.get("category");
            if (category.equals("furniture")) {
                itemsFound = this.furnitureDataAccessObject.getItemsByFilters(
                        filteredAttributes, currentStudent);
            } else if (category.equals("clothing")) {
                itemsFound = this.clothingDataAccessObject.getItemsByFilters(
                        filteredAttributes, currentStudent);
            } else if (category.equals("schoolItem")) {
                itemsFound = this.schoolItemDataAccessObject.getItemsByFilters(
                        filteredAttributes, currentStudent);
            } else if (category.equals("technology")) {
                itemsFound = this.technologyDataAccessObject.getItemsByFilters(
                        filteredAttributes, currentStudent);
            } else {
                throw new IOException("The chosen category is invalid.");
            }
            searchPresenter.prepareSuccessView(new SearchOutputData(itemsFound));

        } catch (IOException e) {
            searchPresenter.prepareFailView(
                    "There was an error in searching through the database.");
        }
    }
}
