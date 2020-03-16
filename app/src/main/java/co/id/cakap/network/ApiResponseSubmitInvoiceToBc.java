package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.SubmitInvoiceToBcData;
import co.id.cakap.data.SubmitInvoiceToMbData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSubmitInvoiceToBc {
    @SerializedName("messages")
    private String messages;
    @SerializedName("info")
    private String info;
    @SerializedName("data")
    private SubmitInvoiceToBcData data;
}
