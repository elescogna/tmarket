package use_case.Home;

import java.io.IOException;

public class HomeInteractor implements HomeInputBoundary {
    final HomeDataAccessInterface userDataAccessObject;
    final HomeOutputBoundary homePresenter;

    public HomeInteractor(HomeDataAccessInterface homeDataAccessInterface,
                           HomeOutputBoundary homeOutputBoundary) {
        this.userDataAccessObject = homeDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
    }

    @Override
    public void execute() {
        try {
            HomeOutputData homeOutputData = new HomeOutputData(userDataAccessObject.getAllItems());
            homePresenter.prepareSuccessView(homeOutputData);
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
    }
}
