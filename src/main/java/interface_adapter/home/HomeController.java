package interface_adapter.home;

import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeController {
    final HomeInputBoundary homeInteractor;
    /**
     * Constructor for HomeController using the interactor given.
     *
     * @param homeInteractor the interactor with which to make the HomeController
     */
    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    /**
     * Creates input data and calls the interactor with this input data.
     */
    public void execute() {
        HomeInputData homeInputData = new HomeInputData();
        this.homeInteractor.execute(homeInputData);
    }
}
