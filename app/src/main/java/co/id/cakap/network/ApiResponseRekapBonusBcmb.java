package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.data.SubmitCashbillData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseRekapBonusBcmb {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<ActivityRekapBnsBcmbData> data;
}
