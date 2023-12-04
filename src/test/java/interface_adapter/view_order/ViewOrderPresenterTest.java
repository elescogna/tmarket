package interface_adapter.view_order;

import entities.Order;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;
import use_case.view_order.ViewOrderOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewOrderPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewOrderViewModel viewOrderViewModel = new ViewOrderViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ViewOrderPresenter
        ViewOrderPresenter viewOrderPresenter = new ViewOrderPresenter(viewOrderViewModel, viewManagerModel, homeViewModel);

        // Create dummy ViewOrderOutputData
        ArrayList<String> directions= new ArrayList<String>();
        directions.add("Follow these directions");
        ViewOrderOutputData viewOrderOutputData = new ViewOrderOutputData(new Order("id", "abc@gmail.com", "xyz@gmail.com", "itemid", "777 Bay Street", "Chair", ""),  "Chair", null, directions);

        // Call the method to be tested
        viewOrderPresenter.prepareSuccessView(viewOrderOutputData);

        // Verify the expected state changes
        ViewOrderState viewOrderState = viewOrderViewModel.getState();
        assertEquals("Chair", viewOrderState.getCurrentOrder().getItemName());
        assertEquals("Chair", viewOrderState.getCurrentItemNameToShow());
        assertEquals("Follow these directions", viewOrderState.getCurrentDirections().get(0));
        assertEquals(viewOrderState, viewOrderViewModel.getState());
        assertEquals(viewOrderViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewOrderViewModel viewOrderViewModel = new ViewOrderViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ViewOrderPresenter
        ViewOrderPresenter viewOrderPresenter = new ViewOrderPresenter(viewOrderViewModel, viewManagerModel, homeViewModel);

        // Call the method to be tested
        viewOrderPresenter.prepareFailView("Order not found");

        // Verify the expected state changes
        ViewOrderState viewOrderState = viewOrderViewModel.getState();
        assertEquals("Order not found", viewOrderState.getCurrentOrderError());
        assertEquals(viewOrderState, viewOrderViewModel.getState());
        assertEquals(homeViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}
