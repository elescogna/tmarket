package use_case.searching;

public class SearchingInteractor implements SearchingInputBoundary {
    SearchingOutputBoundary searchingPresenter;

    public SearchingInteractor(SearchingOutputBoundary searchingPresenter) {
        this.searchingPresenter = searchingPresenter;
    }

    @Override
    public void execute(SearchingInputData searchingInputData) {
        System.out.println("Searching Interactor" + searchingInputData.getCurrentStudent());
        SearchingOutputData searchingOutputData = new SearchingOutputData(searchingInputData.getCurrentStudent());
        searchingPresenter.prepareSearchView(searchingOutputData);
    }
}
