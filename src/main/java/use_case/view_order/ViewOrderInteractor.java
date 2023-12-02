package use_case.view_order;

import entities.Order;
import java.io.IOException;
import java.util.ArrayList;

public class ViewOrderInteractor implements ViewOrderInputBoundary {
    final ViewOrderDataAccessInterface orderDataAccessObject;
    final ViewOrderOutputBoundary viewOrderPresenter;

    public ViewOrderInteractor(ViewOrderDataAccessInterface orderDataAccessObject,
            ViewOrderOutputBoundary viewOrderPresenter) {
        this.orderDataAccessObject = orderDataAccessObject;
        this.viewOrderPresenter = viewOrderPresenter;
    }

    @Override
    public void execute(ViewOrderInputData viewOrderInputData) {
        try {

            String orderIdToGet = viewOrderInputData.getOrderId();
            String currentStudentAddress =
                viewOrderInputData.getCurrentStudentAddress();

            Order ordertoDisplay = orderDataAccessObject.getOrder(orderIdToGet);

            if (ordertoDisplay != null) {
                ArrayList<String> directions = orderDataAccessObject.getDirections(
                        currentStudentAddress, ordertoDisplay.getPickupLocation());

                viewOrderPresenter.prepareSuccessView(new ViewOrderOutputData(
                            ordertoDisplay, viewOrderInputData.getCurrentStudentEmail(),
                            directions));
            } else {
                throw new IOException();
            }

        } catch (IOException e) {
            viewOrderPresenter.prepareFailView("Could not access Atlas database.");
        }
    }
}
