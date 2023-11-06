package use_case.Home;

import java.io.IOException;
import java.util.*;

public class HomeInteractor implements HomeInputBoundary {
    final HomeDataAccessInterface clothingDataAccessObject;
    final HomeDataAccessInterface furnitureDataAccessObject;
    final HomeDataAccessInterface orderDataAccessObject;
    final HomeDataAccessInterface schoolItemDataAccessObject;
    final HomeDataAccessInterface technologyDataAccessObject;
    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface clothingDataAccessInterface, HomeDataAccessInterface furnitureDataAccessInterface,
                          HomeDataAccessInterface orderDataAccessInterface, HomeDataAccessInterface schoolItemDataAccessInterface,
                          HomeDataAccessInterface technologyDataAccessInterface, HomeOutputBoundary homeOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessInterface;
        this.furnitureDataAccessObject = furnitureDataAccessInterface;
        this.orderDataAccessObject = orderDataAccessInterface;
        this.schoolItemDataAccessObject = schoolItemDataAccessInterface;
        this.technologyDataAccessObject = technologyDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute(HomeInputData homeInputData) {
        try {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(clothingDataAccessObject.getAllItems());
            items.add(furnitureDataAccessObject.getAllItems());
            items.add(orderDataAccessObject.getAllItems());
            items.add(schoolItemDataAccessObject.getAllItems());
            items.add(technologyDataAccessObject.getAllItems());
            HomeOutputData homeOutputData = new HomeOutputData(items);
            homePresenter.prepareSuccessView(homeOutputData);
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
    }
}
