package interface_adapter;

public class HomeController {
    final HomeInputBoundary homeInteractor;
    public HomeController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute() {
    }
}
