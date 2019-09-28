package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.ResultDataLogin;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
public class ApiResponseSession {
    @SerializedName("messages")
    private String messages;
    @SerializedName("result")
    private ResultDataLogin result;
}
