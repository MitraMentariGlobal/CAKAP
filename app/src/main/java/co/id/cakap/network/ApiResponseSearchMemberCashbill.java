package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.SearchMemberData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSearchMemberCashbill {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private OperationUserStatusData data;
}
