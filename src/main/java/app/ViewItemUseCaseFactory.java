package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.create_order.CreateOrderViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.JOptionPane;
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

            return new ViewItemView(viewItemViewModel, homeViewModel,
                    createOrderViewModel, viewManagerModel,
                    contactViewModel);
        }
}
