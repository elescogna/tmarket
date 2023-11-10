package use_case.search;

import java.util.HashMap;
import java.util.HashSet;

public class SearchInputData {
    private HashMap<String, String> filteredAttributes;

    public SearchInputData(HashMap<String, String> filteredAttributes) {
        this.filteredAttributes = filteredAttributes;
    }

    public void setFilteredAttributes(HashMap<String, String> filteredAttributes) {
        this.filteredAttributes = filteredAttributes;
    }

    public HashMap<String, String> getFilteredAttributes() {
        return this.filteredAttributes;
    }

    public String getSingleAttribute(String attribute) {
        // TODO: for this to work we need to force the user to select something from all the filterable attribute
        // even if one of the options we put has to be "other" or "none"
        return this.filteredAttributes.get(attribute);
    }
}
