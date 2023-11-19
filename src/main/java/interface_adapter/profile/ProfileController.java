package interface_adapter.profile;

import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileOutputBoundary;

public class ProfileController {

   final ProfileInputBoundary profileInteractor;
   public ProfileController (ProfileInputBoundary profileInteractor){
       this.profileInteractor = profileInteractor;
   }
   public void execute(String id){
       ProfileInputData  profileInputData = new ProfileInputData(id);
       this.profileInteractor.execute(profileInputData);
   }

}
