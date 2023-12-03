package use_case.view_order;

import entities.Order;
import java.io.IOException;
import java.util.ArrayList;

public interface ViewOrderDataAccessInterface {
    Order getOrder(String idToGet) throws IOException;

    ArrayList<String> getDirections(String currentStudentAddress,
            String pickupAddress) throws IOException;
}
