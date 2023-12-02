package interface_adapter.view_order;

import use_case.view_order.ViewOrderInputBoundary;
import use_case.view_order.ViewOrderInputData;

public class ViewOrderController {
    final ViewOrderInputBoundary viewOrderInteractor;

    public ViewOrderController(ViewOrderInputBoundary viewOrderInteractor) {
        this.viewOrderInteractor = viewOrderInteractor;
    }

    public void execute(String orderId, String currentStudentEmail) {
        ViewOrderInputData viewOrderInputData =
            new ViewOrderInputData(orderId, currentStudentEmail,);

        this.viewOrderInteractor.execute(viewOrderInputData);
    }
}
