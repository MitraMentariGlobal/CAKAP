package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.BankData;
import co.id.cakap.data.KotaData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseKota {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<KotaData> data;
}
