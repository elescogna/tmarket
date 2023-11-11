package interface_adapter.create_order;

import entities.Item;
import use_case.create_order.CreateOrderInputBoundary;
import use_case.create_order.CreateOrderInputData;

public class CreateOrderController {

    final CreateOrderInputBoundary createOrderUseCaseInteractor;
    public CreateOrderController(CreateOrderInputBoundary createOrderUseCaseInteractor) {
        this.createOrderUseCaseInteractor = createOrderUseCaseInteractor;
    }

    public void execute(Item item, String sellerEmail, String buyerEmail, String sameAddress, String otherAddress) {
        CreateOrderInputData createOrderInputData = new CreateOrderInputData(
                item, sellerEmail, buyerEmail, sameAddress, otherAddress);

        createOrderUseCaseInteractor.execute(createOrderInputData);
    }
}

