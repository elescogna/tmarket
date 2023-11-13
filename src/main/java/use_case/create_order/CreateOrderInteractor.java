package use_case.create_order;

import entities.Clothing;
import entities.Furniture;
import entities.SchoolItem;

public class CreateOrderInteractor implements CreateOrderInputBoundary {
    final CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessObject;
    final CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasClothingDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessObject;

    final CreateOrderOutputBoundary createOrderPresenter;

    public CreateOrderInteractor(CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessInterface,
                                 CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessInterface,
                                 CreateOrderDataAccessInterfaceItem atlasClothingDataAccessInterface,
                                 CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessInterface,
                                 CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessInterface,
                                 CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessInterface,
                                 CreateOrderOutputBoundary createOrderOutputBoundary) {
        this.atlasOrderDataAccessObject = atlasOrderDataAccessInterface;
        this.atlasStudentDataAccessObject = atlasStudentDataAccessInterface;
        this.atlasClothingDataAccessObject = atlasClothingDataAccessInterface;
        this.atlasFurnitureDataAccessObject = atlasFurnitureDataAccessInterface;
        this.atlasSchoolItemDataAccessObject = atlasSchoolItemDataAccessInterface;
        this.atlasTechnologyDataAccessObject = atlasTechnologyDataAccessInterface;
        this.createOrderPresenter = createOrderOutputBoundary;
    }

    @Override
    public void execute(CreateOrderInputData createOrderInputData) {
        // Create new order & save to database
        // Update item to be sold

        if (!atlasStudentDataAccessObject.existsByEmail(createOrderInputData.getStudent().getUoftEmail())) {
            createOrderPresenter.prepareFailView("Seller e-mail doesn't exist.");
        } else if (!atlasStudentDataAccessObject.existsByEmail(createOrderInputData.getBuyerEmail())) {
            createOrderPresenter.prepareFailView("Buyer e-mail doesn't exist.");
        } else if (createOrderInputData.getSameAddress().equals("Yes")) {
            String orderId = createOrderInputData.getStudent().getUoftEmail().substring(0, createOrderInputData.getStudent().getUoftEmail().indexOf('@')).concat(createOrderInputData.getBuyerEmail().substring(0, 1));
            atlasOrderDataAccessObject.create(orderId, createOrderInputData.getBuyerEmail(), createOrderInputData.getStudent().getUoftEmail(),
                    createOrderInputData.getItem(), createOrderInputData.getItem().getPickupAddress());
            if (createOrderInputData.getItem() instanceof Clothing) {
                atlasClothingDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof Furniture) {
                atlasFurnitureDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof SchoolItem) {
                atlasSchoolItemDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else {
                atlasTechnologyDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            }
            createOrderPresenter.prepareSuccessView();
        } else {
            String orderId = createOrderInputData.getStudent().getUoftEmail().substring(0, createOrderInputData.getStudent().getUoftEmail().indexOf('@')).concat(createOrderInputData.getBuyerEmail().substring(0, 1));
            atlasOrderDataAccessObject.create(orderId, createOrderInputData.getBuyerEmail(), createOrderInputData.getStudent().getUoftEmail(),
                    createOrderInputData.getItem(), createOrderInputData.getOtherAddress());
            if (createOrderInputData.getItem() instanceof Clothing) {
                atlasClothingDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof Furniture) {
                atlasFurnitureDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof SchoolItem) {
                atlasSchoolItemDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            } else {
                atlasTechnologyDataAccessObject.updateSoldYet(createOrderInputData.getItem().getId());
            }
            createOrderPresenter.prepareSuccessView();
        }
    }
}
