package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.ActivityReqInvMbData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseActivityReqInvoiceMb {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<ActivityReqInvMbData> data;
}
