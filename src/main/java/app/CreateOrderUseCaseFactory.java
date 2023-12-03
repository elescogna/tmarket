package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderPresenter;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.create_order.*;
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
                return new CreateOrderView(createOrderController, createOrderViewModel, homeViewModel, viewManagerModel);
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
}
