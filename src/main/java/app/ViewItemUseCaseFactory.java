package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.go_create_order.GoCreateOrderController;
import interface_adapter.go_create_order.GoCreateOrderPresenter;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.JOptionPane;
import use_case.go_create_order.GoCreateOrderInputBoundary;
import use_case.go_create_order.GoCreateOrderInteractor;
import use_case.go_create_order.GoCreateOrderOutputBoundary;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.home.HomeDataAccessInterface;
import use_case.view_item.ViewItemDataAccessInterface;
import use_case.view_item.ViewItemInputBoundary;
import use_case.view_item.ViewItemInteractor;
import use_case.view_item.ViewItemOutputBoundary;
import view.ViewItemView;

public class ViewItemUseCaseFactory {

    /** Prevent instantiation. */
    private ViewItemUseCaseFactory() {}

    public static ViewItemView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                ViewItemViewModel viewItemViewModel, ContactViewModel contactViewModel,
                CreateOrderViewModel createOrderViewModel,
                HomeDataAccessInterface clothingHomeDataAccessObject,
                HomeDataAccessInterface furnitureHomeDataAccessObject,
                HomeDataAccessInterface schoolItemHomeDataAccessObject,
                HomeDataAccessInterface technologyHomeDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject) {

            try {
                GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);
                GoCreateOrderController goCreateOrderController =
                    createGoCreateOrderUseCase(createOrderViewModel, viewManagerModel);

                return new ViewItemView(viewItemViewModel, goHomeController,
                        goCreateOrderController, viewManagerModel,
                        contactViewModel);

            } catch (IOException e) {
                // TODO: what should this actually print out?
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

    private static GoCreateOrderController
        createGoCreateOrderUseCase(CreateOrderViewModel createOrderViewModel,
                ViewManagerModel viewManagerModel)
            throws IOException {
            GoCreateOrderOutputBoundary goCreateOrderOutputBoundary =
                new GoCreateOrderPresenter(createOrderViewModel, viewManagerModel);

            GoCreateOrderInputBoundary goCreateOrderInteractor =
                new GoCreateOrderInteractor(goCreateOrderOutputBoundary);

            return new GoCreateOrderController(goCreateOrderInteractor);
        }
}
