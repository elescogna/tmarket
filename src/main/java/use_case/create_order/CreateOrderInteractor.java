package use_case.create_order;

import app.CreateOrderUseCaseFactory;

import java.io.IOException;

public class CreateOrderInteractor implements CreateOrderInputBoundary {
    final CreateOrderDataAccessInterface atlasOrderDataAccessObject;
    final CreateOrderDataAccessInterface atlasStudentDataAccessObject;
    final CreateOrderDataAccessInterface atlasItemDataAccessObject;
    final CreateOrderOutputBoundary createOrderPresenter;

    public CreateOrderInteractor(CreateOrderDataAccessInterface atlasOrderDataAccessInterface,
                                 CreateOrderDataAccessInterface atlasStudentDataAccessInterface,
                                 CreateOrderDataAccessInterface atlasItemDataAccessInterface,
                                 CreateOrderOutputBoundary createOrderOutputBoundary) {
        this.atlasOrderDataAccessObject = atlasOrderDataAccessInterface;
        this.atlasStudentDataAccessObject = atlasStudentDataAccessInterface;
        this.atlasItemDataAccessObject = atlasItemDataAccessInterface;
        this.createOrderPresenter = createOrderOutputBoundary;
    }

    @Override
    public void execute(CreateOrderInputData createOrderInputData) {
        // Create new order & save to database
        // Update item to be sold

        if (!atlasStudentDataAccessObject.existsByEmail(createOrderInputData.getSellerEmail())) {
            createOrderPresenter.prepareFailView("Seller e-mail doesn't exist.");
        } else if (!atlasStudentDataAccessObject.existsByEmail(createOrderInputData.getBuyerEmail())) {
            createOrderPresenter.prepareFailView("Buyer e-mail doesn't exist.");
        } else if (createOrderInputData.getSameAddress().equals("Yes")) {
            String orderId = createOrderInputData.getSellerEmail().substring(0, createOrderInputData.getSellerEmail().indexOf('@')).concat(createOrderInputData.getBuyerEmail().substring(0, 1));
            atlasOrderDataAccessObject.create(orderId, createOrderInputData.getBuyerEmail(), createOrderInputData.getSellerEmail(),
                    createOrderInputData.getItem(), createOrderInputData.getItem().getPickupAddress());
            atlasItemDataAccessObject.update(createOrderInputData.getItem().getId());
            createOrderPresenter.prepareSuccessView();
        } else {
            String orderId = createOrderInputData.getSellerEmail().substring(0, createOrderInputData.getSellerEmail().indexOf('@')).concat(createOrderInputData.getBuyerEmail().substring(0, 1));
            atlasOrderDataAccessObject.create(orderId, createOrderInputData.getBuyerEmail(), createOrderInputData.getSellerEmail(),
                    createOrderInputData.getItem(), createOrderInputData.getOtherAddress());
            atlasItemDataAccessObject.update(createOrderInputData.getItem().getId());
            createOrderPresenter.prepareSuccessView();
        }
    }
}
