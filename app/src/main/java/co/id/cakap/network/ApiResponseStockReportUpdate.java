package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ReligionData;
import co.id.cakap.data.StockUpdate;
import co.id.cakap.data.StockUpdateData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseStockReportUpdate {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private StockUpdate data;
}
