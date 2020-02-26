package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ActivationKitData extends ActivationKitParentData implements Parcelable {
    @SerializedName("title")
    @Expose
    private String item_code;

    protected ActivationKitData(Parcel in) {
        item_code = in.readString();
    }

    @Generated(hash = 1584810697)
    public ActivationKitData(String item_code) {
        this.item_code = item_code;
    }

    @Generated(hash = 659501648)
    public ActivationKitData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getItem_code() {
        return this.item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public static final Creator<ActivationKitData> CREATOR = new Creator<ActivationKitData>() {
        @Override
        public ActivationKitData createFromParcel(Parcel in) {
            return new ActivationKitData(in);
        }

        @Override
        public ActivationKitData[] newArray(int size) {
            return new ActivationKitData[size];
        }
    };
}
