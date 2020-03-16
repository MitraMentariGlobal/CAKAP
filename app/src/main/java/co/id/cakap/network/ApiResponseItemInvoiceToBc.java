package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.ItemShopData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseItemInvoiceToBc {
    @SerializedName("messages")
    private String messages;
    @SerializedName("url")
    private String url;
    @SerializedName("data")
    private List<ItemShopData> data;
}
