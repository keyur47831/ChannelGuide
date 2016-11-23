package net.whatsbeef.showguide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.
 */
public class ShowTimeModel implements Serializable {

    private List<Result> results = new ArrayList<Result>();
    private Integer count;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}