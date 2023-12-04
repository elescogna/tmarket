package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

import javax.swing.*;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Creates a new ProfilePresenter with the ViewManagerModel and the ProfileViewModel given.
     *
     * @param viewManagerModel the ViewManagerModel with which to create the ProfilePresenter
     * @param profileViewModel the ProfilePresenter with which to create the ProfilePresenter
     */
    public ProfilePresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares to display the ProfileView with the output data given.
     *
     * @param response the output data with which to display the ProfileView
     */
    @Override
    public void prepareSuccessView(ProfileOutputData response) {

        ProfileState profileState = new ProfileState();

        profileState.setUoftEmail(response.getUoftEmail());
        profileState.setPassword(response.getPassword());
        profileState.setHomeAddress(response.getHomeAddress());
        profileState.setName(response.getName());
        profileState.setOrders(response.getAllOrders());
        profileState.setPostedItems(response.getAllItems());
        profileState.setStudent(response.getCurrentStudent());


        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to display the error message given.
     *
     * @param error the error message to display
     */
    @Override
    public void prepareFailView(String error) {
        ProfileState profileState = profileViewModel.getState();
        profileState.setStudentNotFoundError(error);
        profileViewModel.firePropertyChanged();
    }

}
