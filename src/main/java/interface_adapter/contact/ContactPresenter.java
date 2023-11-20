package interface_adapter.contact;
import interface_adapter.ViewManagerModel;
import interface_adapter.view_item.ViewItemViewModel;
import use_case.contact.ContactOutputBoundary;
import use_case.contact.ContactOutputData;

public class ContactPresenter implements ContactOutputBoundary {

  @Override
  public void prepareSuccessView() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'prepareSuccessView'");
  }

  @Override
  public void prepareFailView(String error) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'prepareFailView'");
  }
}
