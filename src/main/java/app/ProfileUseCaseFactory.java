package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import java.io.IOException;
import javax.swing.*;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import view.ProfileView;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory() {}
    public static ProfileView
    create(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel,
           HomeViewModel homeViewModel,
           ProfileDataAccessInterface studentDataAccessObject,
           ProfileDataAccessInterface clothingDataAccessObject,
           ProfileDataAccessInterface furnitureDataAccessObject,
           ProfileDataAccessInterface schoolItemDataAccessObject,
           ProfileDataAccessInterface technologyDataAccessObject,
           ProfileDataAccessInterface orderDataAccessObject) {
        try {
            ProfileController profileController = createProfileUseCase(
                    viewManagerModel, profileViewModel,
                    studentDataAccessObject,
                    clothingDataAccessObject,
                    furnitureDataAccessObject,
                    schoolItemDataAccessObject,
                    technologyDataAccessObject,
                    orderDataAccessObject);
            GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);

            return new ProfileView(profileController, profileViewModel, goHomeController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }

        return null;
    }

    private static GoHomeController
    createGoHomeUseCase(ViewManagerModel viewManagerModel,
                        HomeViewModel homeViewModel) {
        GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

        GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

        return new GoHomeController(goHomeInteractor);
    }

    private static ProfileController createProfileUseCase(
            ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
            ProfileDataAccessInterface studentDataAccessInterface,
            ProfileDataAccessInterface clothingDataAccessInterface,
            ProfileDataAccessInterface furnitureDataAccessInterface,
            ProfileDataAccessInterface schoolItemDataAccessInterface,
            ProfileDataAccessInterface technologyDataAccessInterface,
            ProfileDataAccessInterface orderDataAccessInterface)
            throws IOException {
        ProfileOutputBoundary profileOutputBoundary =
                new ProfilePresenter(viewManagerModel, profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor(studentDataAccessInterface,
                clothingDataAccessInterface,
                furnitureDataAccessInterface,
                schoolItemDataAccessInterface,
                technologyDataAccessInterface,
                orderDataAccessInterface,
                profileOutputBoundary);

        return new ProfileController(profileInteractor);
    }
}


