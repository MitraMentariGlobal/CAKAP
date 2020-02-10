package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.ReligionData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseStockReportCardItem {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<ItemStockCard> data;
}
