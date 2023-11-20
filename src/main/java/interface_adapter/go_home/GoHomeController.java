package interface_adapter.go_home;

import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInputData;

public class GoHomeController {
    final GoHomeInputBoundary goHomeInteractor;

    public GoHomeController(GoHomeInputBoundary goHomeInteractor) {
        this.goHomeInteractor = goHomeInteractor;
    }

    public void execute() {
        GoHomeInputData goHomeInputData = new GoHomeInputData();
        this.goHomeInteractor.execute(goHomeInputData);
    }
}
