package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_order.CreateOrderController;
import interface_adapter.create_order.CreateOrderPresenter;
import interface_adapter.create_order.CreateOrderViewModel;
import use_case.create_order.CreateOrderDataAccessInterface;
import use_case.create_order.CreateOrderInputBoundary;
import use_case.create_order.CreateOrderInteractor;
import use_case.create_order.CreateOrderOutputBoundary;
import view.CreateOrderView;

import javax.swing.*;
import java.io.IOException;

public class CreateOrderUseCaseFactory {
    private CreateOrderUseCaseFactory() {}

    public static CreateOrderView create(
            ViewManagerModel viewManagerModel, ViewItemViewModel viewItemViewModel,
            CreateOrderViewModel createOrderViewModel, CreateOrderDataAccessInterface atlasOrderDataAccessObject,
            CreateOrderDataAccessInterface atlasStudentDataAccessObject, CreateOrderDataAccessInterface atlasItemDataAccessObject) {

        try {
            CreateOrderController createOrderController = createCreateOrderUseCase(viewManagerModel, createOrderViewModel,
                    viewItemViewModel, atlasOrderDataAccessObject, atlasStudentDataAccessObject, atlasItemDataAccessObject);
            return new CreateOrderView(createOrderController, createOrderViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open database.");
        }

        return null;
    }

    private static CreateOrderController createCreateOrderUseCase(ViewManagerModel viewManagerModel, CreateOrderViewModel createOrderViewModel,
                                                                  ViewItemViewModel viewItemViewModel, CreateOrderDataAccessInterface atlasOrderDataAccessObject,
                                                                  CreateOrderDataAccessInterface atlasStudentDataAccessObject, CreateOrderDataAccessInterface atlasItemDataAccessObject) throws IOException {

        CreateOrderOutputBoundary createOrderOutputBoundary = new CreateOrderPresenter(viewManagerModel, createOrderViewModel, viewItemViewModel);

        CreateOrderInputBoundary createOrderInteractor = new CreateOrderInteractor(
                atlasOrderDataAccessObject, atlasStudentDataAccessObject, atlasItemDataAccessObject, createOrderOutputBoundary);

        return new CreateOrderController(createOrderInteractor);
    }
}
