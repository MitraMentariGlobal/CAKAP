package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.SubmitInvoiceToBcData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseAddEditAddress {
    @SerializedName("messages")
    private String messages;
}
