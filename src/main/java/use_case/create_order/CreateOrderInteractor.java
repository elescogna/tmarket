package use_case.create_order;

import entities.Clothing;
import entities.Furniture;
import entities.Order;
import entities.SchoolItem;
import java.io.IOException;

public class CreateOrderInteractor implements CreateOrderInputBoundary {
    final CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessObject;
    final CreateOrderDataAccessInterfaceStudent atlasStudentDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasClothingDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasFurnitureDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasSchoolItemDataAccessObject;
    final CreateOrderDataAccessInterfaceItem atlasTechnologyDataAccessObject;

    final CreateOrderOutputBoundary createOrderPresenter;

    public CreateOrderInteractor(
            CreateOrderDataAccessInterfaceOrder atlasOrderDataAccessInterface,
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

        boolean buyerEmailExists = false;

        try {
            buyerEmailExists = atlasStudentDataAccessObject.existsByEmail(
                    createOrderInputData.getBuyerEmail());
        } catch (IOException e) {
            this.createOrderPresenter.prepareFailView(
                    "Cannot access Atlas database.");
        }

        if (!buyerEmailExists) {
            createOrderPresenter.prepareFailView("Buyer e-mail doesn't exist.");
        } else if (createOrderInputData.getSameAddress().equals("Yes")) {
            if (createOrderInputData.getItem() instanceof Clothing) {
                atlasClothingDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof Furniture) {
                atlasFurnitureDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof SchoolItem) {
                atlasSchoolItemDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else {
                atlasTechnologyDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            }
            createOrderPresenter.prepareSuccessView();
        } else {
            if (createOrderInputData.getItem() instanceof Clothing) {
                atlasClothingDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof Furniture) {
                atlasFurnitureDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else if (createOrderInputData.getItem() instanceof SchoolItem) {
                atlasSchoolItemDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            } else {
                atlasTechnologyDataAccessObject.updateSoldYet(
                        createOrderInputData.getItem().getId());
            }
            createOrderPresenter.prepareSuccessView();
        }
    }
}
