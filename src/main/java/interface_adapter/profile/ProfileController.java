package interface_adapter.profile;

import entities.Student;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileOutputBoundary;

public class ProfileController {

   final ProfileInputBoundary profileInteractor;
   public ProfileController (ProfileInputBoundary profileInteractor){
       this.profileInteractor = profileInteractor;
   }
   public void execute(Student student){
       ProfileInputData  profileInputData = new ProfileInputData(student);
       this.profileInteractor.execute(profileInputData);
   }

}
