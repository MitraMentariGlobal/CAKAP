package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

import java.util.List;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
//@Entity
public class StockUpdate implements Parcelable {
    @SerializedName("note")
    @Expose
    private List<InfoData> note;

    @SerializedName("data")
    @Expose
    private List<StockUpdateData> data;

    protected StockUpdate(Parcel in) {
        note = in.createTypedArrayList(InfoData.CREATOR);
        data = in.createTypedArrayList(StockUpdateData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(note);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StockUpdate> CREATOR = new Creator<StockUpdate>() {
        @Override
        public StockUpdate createFromParcel(Parcel in) {
            return new StockUpdate(in);
        }

        @Override
        public StockUpdate[] newArray(int size) {
            return new StockUpdate[size];
        }
    };
}
