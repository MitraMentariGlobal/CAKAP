package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.RestockInvoiceData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseRestockInvoice {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<RestockInvoiceData> data;
}
