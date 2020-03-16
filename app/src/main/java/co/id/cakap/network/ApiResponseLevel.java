package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.LevelData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseLevel {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<LevelData> data;
}
