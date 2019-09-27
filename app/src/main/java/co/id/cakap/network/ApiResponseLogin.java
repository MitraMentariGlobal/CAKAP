package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ResultData;
import co.id.cakap.data.ResultDataLogin;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
public class ApiResponseLogin {
    @SerializedName("messages")
    private String messages;
    @SerializedName("result")
    private ResultDataLogin result;
}
