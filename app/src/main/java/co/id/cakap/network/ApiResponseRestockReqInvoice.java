package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.RestockReqInvoiceData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseRestockReqInvoice {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<RestockReqInvoiceData> data;
}
