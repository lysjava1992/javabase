
package com.simple.thingsboard.server.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public abstract class SearchTextBased extends BaseData {

    private static final long serialVersionUID = -539812997348227609L;
    
    public SearchTextBased() {
        super();
    }

    public SearchTextBased(UUID id) {
        super(id);
    }
    
    public SearchTextBased(SearchTextBased searchTextBased) {
        super(searchTextBased);
    }
    
    @JsonIgnore
    public abstract String getSearchText(); 

}
