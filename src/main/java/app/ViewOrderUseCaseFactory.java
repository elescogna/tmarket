package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_order.ViewOrderViewModel;
import java.io.IOException;
import javax.swing.JOptionPane;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import view.ViewOrderView;

public class ViewOrderUseCaseFactory {

    /** Prevent instantiation. */
    private ViewOrderUseCaseFactory() {}

    public static ViewOrderView create(ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            ViewOrderViewModel viewOrderViewModel) {

        try {
            GoHomeController goHomeController =
                createGoHomeUseCase(viewManagerModel, homeViewModel);

            return new ViewOrderView(viewOrderViewModel, goHomeController);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }

        return null;
    }

    private static GoHomeController
        createGoHomeUseCase(ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel) throws IOException {
            GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

            GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

            return new GoHomeController(goHomeInteractor);
        }
}
