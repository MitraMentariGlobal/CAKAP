package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.data.OperationUserStatusData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseInvoiceToMb {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<ActivityInvToMbData> data;
}
