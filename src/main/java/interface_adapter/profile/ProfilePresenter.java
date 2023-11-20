package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;
    private ViewManagerModel viewManagerModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData response) {

        ProfileState profileState = new ProfileState();

        profileState.setUoftEmail(response.getUoftEmail());
        profileState.setPassword(response.getPassword());
        profileState.setOrders(response.getOrders());
        profileState.setHomeAddress(response.getHomeAddress());
        profileState.setPostedItems(response.getPostedItems());
        profileState.setName(response.getName());


        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ProfileState profileState = profileViewModel.getState();
        profileState.setStudentNotFoundError(error);
        profileViewModel.firePropertyChanged();
    }

}