package use_case.view_order;

import entities.Item;
import entities.Order;
import java.io.IOException;

public class ViewOrderInteractor implements ViewOrderInputBoundary {
    final ViewOrderItemDataAccessInterface clothingDataAccessObject;
    final ViewOrderItemDataAccessInterface furnitureDataAccessObject;
    final ViewOrderItemDataAccessInterface schoolItemDataAccessObject;
    final ViewOrderItemDataAccessInterface technologyDataAccessObject;
    final ViewOrderOrderDataAccessInterface orderDataAccessObject;
    final ViewOrderOutputBoundary viewOrderPresenter;

    public ViewOrderInteractor(
            ViewOrderItemDataAccessInterface clothingDataAccessObject,
            ViewOrderItemDataAccessInterface furnitureDataAccessObject,
            ViewOrderItemDataAccessInterface schoolItemDataAccessObject,
            ViewOrderItemDataAccessInterface technologyDataAccessObject,
            ViewOrderOrderDataAccessInterface orderDataAccessObject,
            ViewOrderOutputBoundary viewOrderPresenter) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.orderDataAccessObject = orderDataAccessObject;
        this.viewOrderPresenter = viewOrderPresenter;
            }

    @Override
    public void execute(ViewOrderInputData viewOrderInputData) {
        try {
            Order ordertoDisplay;
            Item itemToDisplay;

            String orderIdToGet = viewOrderInputData.getOrderId();

            if ((ordertoDisplay = orderDataAccessObject.getOrder(orderIdToGet)) !=
                    null) {
                String itemIdToGet = ordertoDisplay.getItemId();
                if ((itemToDisplay = clothingDataAccessObject.getItem(itemIdToGet)) !=
                        null ||
                        (itemToDisplay = furnitureDataAccessObject.getItem(itemIdToGet)) !=
                        null ||
                        (itemToDisplay = schoolItemDataAccessObject.getItem(itemIdToGet)) !=
                        null ||
                        (itemToDisplay = technologyDataAccessObject.getItem(itemIdToGet)) !=
                        null) {
                    new ViewOrderOutputData(ordertoDisplay, itemToDisplay.getName());
                } else {
                    throw new IOException("Item ID of the order not found in database");
                }

            } else {
                throw new IOException("Order with the given ID not found in database.");
            }
        } catch (IOException e) {
            viewOrderPresenter.prepareFailView(
                    "No such item was found in the database.");
        }
    }
}
