package interface_adapter.home;

import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeController {
    final HomeInputBoundary homeInteractor;
    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute() {
        HomeInputData homeInputData = new HomeInputData();
        this.homeInteractor.execute(homeInputData);
    }
}
