package use_case.profile;
import entities.Item;
import entities.Order;
import entities.Student;

import java.io.IOException;
import java.util.*;

public class ProfileInteractor implements ProfileInputBoundary{

    final ProfileDataAccessInterface studentDataAccessObject;
    final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileDataAccessInterface studentDataAccessObject, ProfileOutputBoundary profilePresenter){
        this.studentDataAccessObject = studentDataAccessObject;
        this.profilePresenter = profilePresenter;
    }
    @Override
    public void execute(ProfileInputData profileInputData) {
       try{
            Student student = studentDataAccessObject.getStudentById(profileInputData.getId());
            if (student == null) {
                profilePresenter.prepareFailView("Student not found");
            }
            String name = student.getName();
            String password = student.getPassword();
            String uoftEmail = student.getUoftEmail();
            String homeAddress = student.getHomeAddress();
            int contact = student.getContact();
            ArrayList<Order> orders = student.getOrders();
            ArrayList<Item> postedItems = student.getPostedItems();
            ProfileOutputData profileOutputData = new ProfileOutputData(name, password, uoftEmail, homeAddress, contact, orders, postedItems);
            profilePresenter.prepareSuccessView(profileOutputData);
       }
       catch(IOException error){
           //come up with better error message
           throw new RuntimeException(error);
           //profilePresenter.prepareFailView("Student not found");
       }
    }
}
