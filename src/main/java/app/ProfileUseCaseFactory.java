package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import view.HomeView;
import view.ProfileView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory() {}

    public static ProfileView create(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            ProfileDataAccessInterface studentDataAccessObject) {

        try {
            ProfileController profileController = createProfileUseCase(viewManagerModel, profileViewModel, studentDataAccessObject);
            return new ProfileView(profileController, profileViewModel);
        } catch (IOException e) {
            // TODO: what should this actually print out?
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ProfileController createProfileUseCase(
            ViewManagerModel viewManagerModel,
            ProfileViewModel profileViewModel,
            ProfileDataAccessInterface studentDataAccessObject) throws IOException {

        // Pass this method's parameters to the Presenter.
        ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(viewManagerModel, profileViewModel);

        ProfileInputBoundary profileInteractor = new ProfileInteractor(
                studentDataAccessObject,
                profileOutputBoundary);

        return new ProfileController(profileInteractor);
    }
}
