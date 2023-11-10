package use_case.view_item;

public class ViewItemInteractor implements ViewItemInputBoundary {
    final ViewItemDataAccessInterface clothingDataAccessObject;
    final ViewItemDataAccessInterface furnitureDataAccessObject;
    final ViewItemDataAccessInterface orderDataAccessObject;
    final ViewItemDataAccessInterface schoolItemDataAccessObject;
    final ViewItemDataAccessInterface technologyDataAccessObject;
    final ViewItemOutputBoundary homePresenter;

    public ViewItemInteractor(
            ViewItemDataAccessInterface clothingDataAccessObject,
            ViewItemDataAccessInterface furnitureDataAccessObject,
            ViewItemDataAccessInterface orderDataAccessObject,
            ViewItemDataAccessInterface schoolItemDataAccessObject,
            ViewItemDataAccessInterface technologyDataAccessObject,
            ViewItemOutputBoundary homeOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.orderDataAccessObject = orderDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.homePresenter = homeOutputBoundary;
            }

    @Override
    public void execute(ViewItemInputData viewItemData) {}
}
