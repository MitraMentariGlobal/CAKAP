package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.ProfileData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseJenisKelamin {
    @SerializedName("messages")
    private String messages;
    @SerializedName("data")
    private List<JenisKelaminData> data;
}
