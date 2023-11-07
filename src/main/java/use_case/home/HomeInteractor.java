package use_case.home;

import entities.Item;

import java.io.IOException;
import java.util.*;

public class HomeInteractor implements HomeInputBoundary {
    final HomeDataAccessInterface clothingDataAccessObject;
    final HomeDataAccessInterface furnitureDataAccessObject;
    final HomeDataAccessInterface orderDataAccessObject;
    final HomeDataAccessInterface schoolItemDataAccessObject;
    final HomeDataAccessInterface technologyDataAccessObject;
    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface clothingDataAccessObject, HomeDataAccessInterface furnitureDataAccessObject,
                          HomeDataAccessInterface orderDataAccessObject, HomeDataAccessInterface schoolItemDataAccessObject,
                          HomeDataAccessInterface technologyDataAccessObject, HomeOutputBoundary homeOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.orderDataAccessObject = orderDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute(HomeInputData homeInputData) {
        try {
            ArrayList<Item> items = new ArrayList<>();
            items.addAll(clothingDataAccessObject.getAllItems());
            items.addAll(furnitureDataAccessObject.getAllItems());
            items.addAll(orderDataAccessObject.getAllItems());
            items.addAll(schoolItemDataAccessObject.getAllItems());
            items.addAll(technologyDataAccessObject.getAllItems());
            HomeOutputData homeOutputData = new HomeOutputData(items);
            homePresenter.prepareSuccessView(homeOutputData);
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
    }
}