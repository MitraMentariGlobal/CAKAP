package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import co.id.cakap.data.ProfileData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseProfileData {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private ProfileData data;
}
