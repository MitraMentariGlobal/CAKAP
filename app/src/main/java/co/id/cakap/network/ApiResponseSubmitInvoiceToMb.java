package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.SubmitCashbillData;
import co.id.cakap.data.SubmitInvoiceToMbData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSubmitInvoiceToMb {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private SubmitInvoiceToMbData data;
}
