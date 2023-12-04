package interface_adapter.create_order;

import entities.Furniture;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;
import use_case.create_order.CreateOrderOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateOrderPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create CreateOrderPresenter
        CreateOrderPresenter createOrderPresenter = new CreateOrderPresenter(viewManagerModel, createOrderViewModel);

        // Create dummy CreateOrderOutputData
        CreateOrderOutputData createOrderOutputData = new CreateOrderOutputData(new Furniture("Chair", "Vintage Teak Chair", 4, 100, 10, false, "91 Charles Street", "", "chair", "", LocalDateTime.now(), 10.0, 10.0, 10.0 ));

        // Call the method to be tested
        createOrderPresenter.prepareSuccessView(createOrderOutputData);

        // Verify the expected state changes
        CreateOrderState createOrderState = createOrderViewModel.getState();
        assertEquals("Chair", createOrderState.getItem().getName());
        assertEquals(createOrderState, createOrderViewModel.getState());
        assertEquals(homeViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateOrderViewModel createOrderViewModel = new CreateOrderViewModel();

        // Create CreateOrderPresenter
        CreateOrderPresenter createOrderPresenter = new CreateOrderPresenter(viewManagerModel, createOrderViewModel);

        // Call the method to be tested
        createOrderPresenter.prepareFailView("Invalid email");

        // Verify the expected state changes
        CreateOrderState createOrderState = createOrderViewModel.getState();
        assertEquals("Invalid email", createOrderState.getEmailError());
        assertEquals(createOrderState, createOrderViewModel.getState());
    }
}
