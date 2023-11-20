package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderPresenter;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.view_item.ViewItemViewModel;
import use_case.create_order.*;
import view.CreateOrderView;

import javax.swing.*;
import java.io.IOException;

public class CreateOrderUseCaseFactory {
    private CreateOrderUseCaseFactory() {}

    public static CreateOrderView create(
            ViewManagerModel viewManagerModel, ViewItemViewModel viewItemViewModel,
            CreateOrderViewModel createOrderViewModel, GoHomeController goHomeController, CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessObject,
            CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessObject, CreateOrderDataAccessInterfaceItem atlasClothingDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessObject, CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessObject,
            CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessObject) {

        try {
            CreateOrderController createOrderController = createCreateOrderUseCase(viewManagerModel, createOrderViewModel,
                    viewItemViewModel, atlasOrderDataAccessObject, atlasStudentDataAccessObject, atlasClothingDataAccessObject,
                    atlasFurnitureDataAccessObject, atlasSchoolItemDataAccessObject, atlasTechnologyDataAccessObject);
            return new CreateOrderView(createOrderController, createOrderViewModel, goHomeController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open database.");
        }

        return null;
    }

    private static CreateOrderController createCreateOrderUseCase(ViewManagerModel viewManagerModel, CreateOrderViewModel createOrderViewModel,
                                                                  ViewItemViewModel viewItemViewModel, CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessObject,
                                                                  CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessObject,
                                                                  CreateOrderDataAccessInterfaceItem atlasClothingDataAccessObject,
                                                                  CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessObject,
                                                                  CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessObject,
                                                                  CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessObject) throws IOException {

        CreateOrderOutputBoundary createOrderOutputBoundary = new CreateOrderPresenter(viewManagerModel, createOrderViewModel, viewItemViewModel);

        CreateOrderInputBoundary createOrderInteractor = new CreateOrderInteractor(
                atlasOrderDataAccessObject, atlasStudentDataAccessObject, atlasClothingDataAccessObject, atlasFurnitureDataAccessObject,
                atlasSchoolItemDataAccessObject, atlasTechnologyDataAccessObject, createOrderOutputBoundary);

        return new CreateOrderController(createOrderInteractor);
    }
}
