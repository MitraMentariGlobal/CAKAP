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
    private String title;

    @SerializedName("item_id")
    @Expose
    private String item_id;

    @SerializedName("qty")
    @Expose
    private String qty;

    protected ActivationKitData(Parcel in) {
        title = in.readString();
        item_id = in.readString();
        qty = in.readString();
    }

    @Generated(hash = 1007888957)
    public ActivationKitData(String title, String item_id, String qty) {
        this.title = title;
        this.item_id = item_id;
        this.qty = qty;
    }

    @Generated(hash = 659501648)
    public ActivationKitData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(item_id);
        dest.writeString(qty);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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
