package use_case.profile;

import entities.Item;
import entities.Order;
import entities.Student;

import java.io.IOException;
import java.util.ArrayList;

public interface ProfileDataAccessInterface {

    Student getStudentByEmail(String id) throws IOException;

    ArrayList<Item> getAllItemsByOwnerID(String ownerId) throws IOException;

    ArrayList<Order> getAllOrdersBySellerEmail(String sellerEmail) throws IOException;
}