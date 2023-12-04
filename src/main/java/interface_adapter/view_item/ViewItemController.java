package interface_adapter.view_item;

import entities.Student;
import use_case.view_item.ViewItemInputBoundary;
import use_case.view_item.ViewItemInputData;

public class ViewItemController {
    final ViewItemInputBoundary viewItemInteractor;

    /**
     * Constructor for the ViewItemController class.
     *
     * @param viewItemInteractor the view interactor with which to make this new controller
     */
    public ViewItemController(ViewItemInputBoundary viewItemInteractor) {
        this.viewItemInteractor = viewItemInteractor;
    }

    /**
     * Takes in all of the input data, puts it in the ViewItemInputData
     * structure, and calls the interactor with the input data.
     *
     * @param itemId the item id to pass to the interactor
     * @param currentStudent the student object to pass to the interactor
     */
    public void execute(String itemId, Student currentStudent) {
        ViewItemInputData viewItemInputData = new ViewItemInputData(itemId, currentStudent);

        this.viewItemInteractor.execute(viewItemInputData);
    }
}
