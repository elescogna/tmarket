package interface_adapter.create_order;

import entities.Item;
import entities.Student;
import use_case.create_order.CreateOrderInputBoundary;
import use_case.create_order.CreateOrderInputData;

public class CreateOrderController {

    final CreateOrderInputBoundary createOrderUseCaseInteractor;
    public CreateOrderController(CreateOrderInputBoundary createOrderUseCaseInteractor) {
        this.createOrderUseCaseInteractor = createOrderUseCaseInteractor;
    }

    public void execute(Item item, Student student, String buyerEmail, String sameAddress, String otherAddress) {
        CreateOrderInputData createOrderInputData = new CreateOrderInputData(
                item, student, buyerEmail, sameAddress, otherAddress);

        createOrderUseCaseInteractor.execute(createOrderInputData);
    }
}

