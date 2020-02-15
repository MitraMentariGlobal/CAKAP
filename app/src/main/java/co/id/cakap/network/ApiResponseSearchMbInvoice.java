package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.OperationUserStatusData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSearchMbInvoice {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private OperationUserStatusData data;
}
