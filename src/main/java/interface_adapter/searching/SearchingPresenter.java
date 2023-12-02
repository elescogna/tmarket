package interface_adapter.searching;

import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.searching.SearchingOutputBoundary;
import use_case.searching.SearchingOutputData;

public class SearchingPresenter implements SearchingOutputBoundary {
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchingPresenter(SearchViewModel searchViewModel,
                              ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
        public void prepareSearchView(SearchingOutputData searchingOutputData) {
        Student currentStudent = searchingOutputData.getStudent();
        searchViewModel.getState().setCurrentStudent(currentStudent);
        searchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
