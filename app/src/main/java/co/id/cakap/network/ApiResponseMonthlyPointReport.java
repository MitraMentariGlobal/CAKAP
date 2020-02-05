package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.LevelData;
import co.id.cakap.data.MonthlyPointReportData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseMonthlyPointReport {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<MonthlyPointReportData> data;
}
