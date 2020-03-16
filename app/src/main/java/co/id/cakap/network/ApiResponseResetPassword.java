package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseResetPassword {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private String data;
}
