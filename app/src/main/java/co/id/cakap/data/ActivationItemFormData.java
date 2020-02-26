package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ActivationItemFormData implements Parcelable {
    @SerializedName("item_form")
    @Expose
    private String item_form;

    protected ActivationItemFormData(Parcel in) {
        item_form = in.readString();
    }

    @Generated(hash = 292112454)
    public ActivationItemFormData(String item_form) {
        this.item_form = item_form;
    }

    @Generated(hash = 561910474)
    public ActivationItemFormData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_form);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getItem_form() {
        return this.item_form;
    }

    public void setItem_form(String item_form) {
        this.item_form = item_form;
    }

    public static final Creator<ActivationItemFormData> CREATOR = new Creator<ActivationItemFormData>() {
        @Override
        public ActivationItemFormData createFromParcel(Parcel in) {
            return new ActivationItemFormData(in);
        }

        @Override
        public ActivationItemFormData[] newArray(int size) {
            return new ActivationItemFormData[size];
        }
    };
}
