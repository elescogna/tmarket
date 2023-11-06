package interface_adapter.home;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary{
    private final HomeViewModel homeViewModel;

    public HomePresenter(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }
    @Override
    public void prepareSuccessView(HomeOutputData response) {
        HomeState homeState = new HomeState();
        homeState.setWantedPosts(response.getWantedPosts());
        this.homeViewModel.setState(homeState);
        homeViewModel.firePropertyChanged();
    }
}
