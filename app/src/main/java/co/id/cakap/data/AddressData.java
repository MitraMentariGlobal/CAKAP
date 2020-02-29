package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class AddressData {
    @SerializedName("default")
    @Expose
    private AddressDefaultData addressDefaultData;

    @SerializedName("history")
    @Expose
    private List<AddressHistoryData> resultData;
}
