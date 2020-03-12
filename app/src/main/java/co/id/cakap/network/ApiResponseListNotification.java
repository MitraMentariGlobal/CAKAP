package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.NotificationApiData;
import co.id.cakap.data.ResultDataLogin;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseListNotification {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<NotificationApiData> data;
}
