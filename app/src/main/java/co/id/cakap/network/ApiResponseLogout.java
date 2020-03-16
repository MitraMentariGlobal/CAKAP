package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseLogout {
    @SerializedName("messages")
    private String messages;
}
