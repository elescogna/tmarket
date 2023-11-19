package interface_adapter.home;

import entities.Student;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;


public class HomeController {
    final HomeInputBoundary homeInteractor;
    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute(Student student) {
        HomeInputData homeInputData = new HomeInputData();
        // need to pass in student
        this.homeInteractor.execute(homeInputData);
    }
}
