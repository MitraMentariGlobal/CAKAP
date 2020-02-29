package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.AddressData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseListAddress {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private AddressData data;
}
