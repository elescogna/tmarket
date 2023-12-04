package interface_adapter.view_item;

import entities.Furniture;
import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;
import use_case.view_item.ViewItemOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewItemPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewItemViewModel viewItemViewModel = new ViewItemViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ViewItemPresenter
        ViewItemPresenter viewItemPresenter = new ViewItemPresenter(viewItemViewModel, viewManagerModel, homeViewModel);

        // Create dummy ViewItemOutputData
        ViewItemOutputData viewItemOutputData = new ViewItemOutputData(new Furniture("Chair", "Vintage Teak Chair", 4, 100, 10, false, "91 Charles Street", "", "chair", "", LocalDateTime.now(), 10.0, 10.0, 10.0 ), null, new Student("clary", "clary", "Art Gallery of Ontario", "clary@mail.utoronto.ca"));

        // Call the method to be tested
        viewItemPresenter.prepareSuccessView(viewItemOutputData);

        // Verify the expected state changes
        ViewItemState viewItemState = viewItemViewModel.getState();
        assertEquals("Chair", viewItemState.getCurrentItem().getName());
        assertEquals(viewItemState, viewItemViewModel.getState());
        assertEquals(viewItemViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewItemViewModel viewItemViewModel = new ViewItemViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ViewItemPresenter
        ViewItemPresenter viewItemPresenter = new ViewItemPresenter(viewItemViewModel, viewManagerModel, homeViewModel);

        // Call the method to be tested
        viewItemPresenter.prepareFailView("Item not found");

        // Verify the expected state changes
        ViewItemState viewItemState = viewItemViewModel.getState();
        assertEquals("Item not found", viewItemState.getCurrentItemError());
        assertEquals(viewItemState, viewItemViewModel.getState());
        assertEquals(homeViewModel.getViewName(), viewManagerModel.getActiveView());
    }
}
