package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ApiResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<ResultData> results;

    public List<ResultData> getResults() {
        return results;
    }

    public void setResults(List<ResultData> results) {
        this.results = results;
    }
}
