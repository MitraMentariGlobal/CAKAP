package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.EbonusData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseEbonusMember {
    @SerializedName("messages")
    private String messages;
    @SerializedName("total_debet")
    private String total_debet;
    @SerializedName("total_kredit")
    private String total_kredit;
    @SerializedName("ewallet")
    private String ewallet;
    @SerializedName("data")
    private List<EbonusData> data;
}
