package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.SubmitCashbillData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSubmitCashbill {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private SubmitCashbillData data;
}
