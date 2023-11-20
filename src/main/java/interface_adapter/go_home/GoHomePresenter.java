package interface_adapter.go_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.go_home.GoHomeOutputBoundary;

public class GoHomePresenter implements GoHomeOutputBoundary {
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public GoHomePresenter(ViewManagerModel viewManagerModel,
                           HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
