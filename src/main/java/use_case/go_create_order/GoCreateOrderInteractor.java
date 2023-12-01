package use_case.go_create_order;

public class GoCreateOrderInteractor implements GoCreateOrderInputBoundary {
    final GoCreateOrderOutputBoundary goCreateOrderPresenter;
    public GoCreateOrderInteractor(
            GoCreateOrderOutputBoundary goCreateOrderOutputBoundary) {
        this.goCreateOrderPresenter = goCreateOrderOutputBoundary;
            }

    public void execute(GoCreateOrderInputData goCreateOrderInputData) {
        GoCreateOrderOutputData goCreateOrderOutputData =
            new GoCreateOrderOutputData(goCreateOrderInputData.getUser(),
                    goCreateOrderInputData.getItem());
        goCreateOrderPresenter.prepareSuccessView(goCreateOrderOutputData);
    }
}
