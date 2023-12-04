package interface_adapter.view_order;

import use_case.view_order.ViewOrderInputBoundary;
import use_case.view_order.ViewOrderInputData;

public class ViewOrderController {
    final ViewOrderInputBoundary viewOrderInteractor;

    /**
     * Constructor for the ViewOrderController with the interactor given.
     *
     * @param viewOrderInteractor the ViewOrderInteractor with which to make the ViewOrderController
     */
    public ViewOrderController(ViewOrderInputBoundary viewOrderInteractor) {
        this.viewOrderInteractor = viewOrderInteractor;
    }

    /**
     * Create input data out of the parameters given and call the view order controller with that data.
     *
     * @param orderId the order ID with which to call the view order controller
     * @param studentEmail the student email with which to call the view order controller
     * @param studentAddress the student address with which to call the view order controller
     */
    public void execute(String orderId, String studentEmail, String studentAddress) {
        ViewOrderInputData viewOrderInputData =
            new ViewOrderInputData(orderId, studentEmail, studentAddress);

        this.viewOrderInteractor.execute(viewOrderInputData);
    }
}
