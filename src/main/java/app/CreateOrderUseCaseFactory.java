package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderPresenter;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.create_order.*;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import view.CreateOrderView;

public class CreateOrderUseCaseFactory {
    private CreateOrderUseCaseFactory() {}

    public static CreateOrderView
        create(ViewManagerModel viewManagerModel, ViewItemViewModel viewItemViewModel,
                CreateOrderViewModel createOrderViewModel, HomeViewModel homeViewModel,
                CreateOrderDataAccessInterface orderDataAccessObject,
                CreateOrderDataAccessInterfaceStudent studentDataAccessObject,
                CreateOrderDataAccessInterfaceItem clothingDataAccessObject,
                CreateOrderDataAccessInterfaceItem furnitureDataAccessObject,
                CreateOrderDataAccessInterfaceItem schoolItemDataAccessObject,
                CreateOrderDataAccessInterfaceItem technologyDataAccessObject) {

            try {
                CreateOrderController createOrderController = createCreateOrderUseCase(
                        viewManagerModel, createOrderViewModel, viewItemViewModel,
                        orderDataAccessObject, studentDataAccessObject,
                        clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessObject, technologyDataAccessObject);
                GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);
                return new CreateOrderView(createOrderController, createOrderViewModel,
                        goHomeController);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open database.");
            }

            return null;
        }

    private static CreateOrderController createCreateOrderUseCase(
            ViewManagerModel viewManagerModel,
            CreateOrderViewModel createOrderViewModel,
            ViewItemViewModel viewItemViewModel,
            CreateOrderDataAccessInterface atlasOrderDataAccessObject,
            CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasClothingDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessObject)
            throws IOException {

            CreateOrderOutputBoundary createOrderOutputBoundary =
                new CreateOrderPresenter(viewManagerModel, createOrderViewModel);

            CreateOrderInputBoundary createOrderInteractor = new CreateOrderInteractor(
                    atlasOrderDataAccessObject, atlasStudentDataAccessObject,
                    atlasClothingDataAccessObject, atlasFurnitureDataAccessObject,
                    atlasSchoolItemDataAccessObject, atlasTechnologyDataAccessObject,
                    createOrderOutputBoundary);

            return new CreateOrderController(createOrderInteractor);
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
}
