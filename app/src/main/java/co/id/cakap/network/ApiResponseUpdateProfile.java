package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.ProfileData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseUpdateProfile {
    @SerializedName("messages")
    private String messages;
}
