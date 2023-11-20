package use_case.go_home;

import interface_adapter.go_home.GoHomeController;

public class GoHomeInteractor implements GoHomeInputBoundary {
    final GoHomeOutputBoundary goHomePresenter;

    public GoHomeInteractor(GoHomeOutputBoundary goHomeOutputBoundary) {
        this.goHomePresenter = goHomeOutputBoundary;
    }

    public void execute(GoHomeInputData goHomeInputData) {
        goHomePresenter.prepareSuccessView();
    }
}
