package interface_adapter;

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
