package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.RegistrationData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseItemSearchRegistration {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<ItemSearchRegistrationData> data;
}
