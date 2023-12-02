package use_case.view_order;

import entities.Order;
import java.io.IOException;

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
            Order ordertoDisplay;

            String orderIdToGet = viewOrderInputData.getOrderId();

            if ((ordertoDisplay = orderDataAccessObject.getOrder(orderIdToGet)) !=
                    null) {
                viewOrderPresenter.prepareSuccessView(new
                        ViewOrderOutputData(ordertoDisplay,
                            viewOrderInputData.getCurrentStudentEmail(), null));
            } else {
                throw new IOException("Order with the given ID not found in database.");
            }
        } catch (IOException e) {
            viewOrderPresenter.prepareFailView("Could not access Atlas database.");
        }
    }
}
