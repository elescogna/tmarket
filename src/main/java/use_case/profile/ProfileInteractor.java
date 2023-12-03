package use_case.profile;
import entities.Item;
import entities.Order;
import entities.Student;

import java.io.IOException;
import java.util.*;

public class ProfileInteractor implements ProfileInputBoundary{

    final ProfileDataAccessInterface studentDataAccessObject;
    final ProfileDataAccessInterface clothingDataAccessObject;
    final ProfileDataAccessInterface furnitureDataAccessObject;
    final ProfileDataAccessInterface schoolItemDataAccessObject;
    final ProfileDataAccessInterface technologyDataAccessObject;
    final ProfileDataAccessInterface orderDataAccessObject;
    final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileDataAccessInterface studentDataAccessObject,
                             ProfileDataAccessInterface clothingDataAccessObject,
                             ProfileDataAccessInterface furnitureDataAccessObject,
                             ProfileDataAccessInterface schoolItemDataAccessObject,
                             ProfileDataAccessInterface technologyDataAccessObject,
                             ProfileDataAccessInterface orderDataAccessObject,
                             ProfileOutputBoundary profilePresenter){
        this.studentDataAccessObject = studentDataAccessObject;
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.orderDataAccessObject = orderDataAccessObject;
        this.profilePresenter = profilePresenter;
    }
    /**
     * Calls all appropriate DAO methods with the given input data and calls
     * the Profile presenter with the output data.
     *
     * @param profileInputData the input data with which to call the DAO methods
     */
    @Override
    public void execute(ProfileInputData profileInputData) {
        try{
            Student student = studentDataAccessObject.getStudentByEmail(profileInputData.getStudent().getUoftEmail());
            if (student == null) {
                profilePresenter.prepareFailView("Student not found");
            } else {
                String name = student.getName();
                String password = student.getPassword();
                String uoftEmail = student.getUoftEmail();
                String homeAddress = student.getHomeAddress();
                ArrayList<Item> items = new ArrayList<>();
                ArrayList<Order> orders = new ArrayList<>();
                items.addAll(clothingDataAccessObject.getAllItemsByOwnerID(student.getId()));
                items.addAll(furnitureDataAccessObject.getAllItemsByOwnerID(student.getId()));
                items.addAll(schoolItemDataAccessObject.getAllItemsByOwnerID(student.getId()));
                items.addAll(technologyDataAccessObject.getAllItemsByOwnerID(student.getId()));
                orders.addAll(orderDataAccessObject.getAllOrdersBySellerEmail(uoftEmail));
                items.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item firstItem, Item secondItem) {
                        return firstItem.getCreationTime().compareTo(secondItem.getCreationTime());
                    }
                });
                Collections.reverse(items);
                ProfileOutputData profileOutputData = new ProfileOutputData(name, password, uoftEmail, homeAddress, items, orders, student);
                profilePresenter.prepareSuccessView(profileOutputData);
            }
        }
        catch(IOException error){
            //come up with better error message
            // throw new RuntimeException(error);
            profilePresenter.prepareFailView("Student not found");
        }
    }
}
