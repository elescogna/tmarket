package interface_adapter.go_create_order;

import entities.Item;
import entities.Student;
import use_case.go_create_order.GoCreateOrderInputBoundary;
import use_case.go_create_order.GoCreateOrderInputData;

public class GoCreateOrderController {
    final GoCreateOrderInputBoundary goCreateOrderInteractor;
    public GoCreateOrderController(GoCreateOrderInputBoundary goCreateOrderInteractor) {
        this.goCreateOrderInteractor = goCreateOrderInteractor;
    }
    public void execute(Student user, Item item) {
        GoCreateOrderInputData goCreateOrderInputData = new GoCreateOrderInputData(user, item);
        this.goCreateOrderInteractor.execute(goCreateOrderInputData);
    }
}