package interface_adapter.view_item;

import entities.Student;
import use_case.view_item.ViewItemInputBoundary;
import use_case.view_item.ViewItemInputData;

public class ViewItemController {
    final ViewItemInputBoundary viewItemInteractor;

    public ViewItemController(ViewItemInputBoundary viewItemInteractor) {
        this.viewItemInteractor = viewItemInteractor;
    }

    public void execute(String itemId, Student currentStudent) {
        ViewItemInputData viewItemInputData = new ViewItemInputData(itemId, currentStudent);

        this.viewItemInteractor.execute(viewItemInputData);
    }
}
