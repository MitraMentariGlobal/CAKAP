package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseItemCashbill {
    @SerializedName("messages")
    private String messages;
    @SerializedName("url")
    private String url;
    @SerializedName("data")
    private List<ItemShopData> data;
}
