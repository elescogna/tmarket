package use_case.view_item;

import entities.Item;
import java.io.IOException;

public class ViewItemInteractor implements ViewItemInputBoundary {
    final ViewItemDataAccessInterface clothingDataAccessObject;
    final ViewItemDataAccessInterface furnitureDataAccessObject;
    final ViewItemDataAccessInterface schoolItemDataAccessObject;
    final ViewItemDataAccessInterface technologyDataAccessObject;
    final ViewItemOutputBoundary viewItemPresenter;

    public ViewItemInteractor(
            ViewItemDataAccessInterface clothingDataAccessObject,
            ViewItemDataAccessInterface furnitureDataAccessObject,
            ViewItemDataAccessInterface schoolItemDataAccessObject,
            ViewItemDataAccessInterface technologyDataAccessObject,
            ViewItemOutputBoundary viewItemOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.viewItemPresenter = viewItemOutputBoundary;
            }

    @Override
    public void execute(ViewItemInputData viewItemData) {
        try {
            Item itemToDisplay;

            // try to find the item in any of the collections
            String itemIdToGet = viewItemData.getItemId();

            if ((itemToDisplay = clothingDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = furnitureDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = schoolItemDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = technologyDataAccessObject.getItem(itemIdToGet)) !=
                    null) {
                viewItemPresenter.prepareSuccessView(new ViewItemOutputData(
                            itemToDisplay, viewItemData.getCurrentStudent()));
            } else { // if the item wasn't found in any collection
                throw new IOException("Item with the given ID not found in database.");
            }
        } catch (IOException e) {
            viewItemPresenter.prepareFailView(
                    "No such item was found in the database.");
        }
    }
}
