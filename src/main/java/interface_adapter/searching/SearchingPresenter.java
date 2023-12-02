package interface_adapter.searching;

import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.search.SearchViewModel;
import use_case.contacting.ContactingOutputData;
import use_case.searching.SearchingInputData;
import use_case.searching.SearchingOutputBoundary;
import view.SearchView;

public class SearchingPresenter implements SearchingOutputBoundary {
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchingPresenter(SearchViewModel searchViewModel,
                              ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSearchView(SearchingInputData searchingInputData) {
        Student currentStudent = searchingInputData.getCurrentStudent();
        System.out.println("Searching Presenter" + currentStudent);
        searchViewModel.getState().setCurrentStudent(currentStudent);
        System.out.println("Search ViewModel" + searchViewModel.getState().getCurrentStudent());
        // not firing state change because nothing needs to be done

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
