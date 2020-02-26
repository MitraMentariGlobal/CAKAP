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
public class ActivationNumberFormData extends ActivationNumberFormParentData implements Parcelable {
    @SerializedName("number_form")
    @Expose
    private String number_form;

    protected ActivationNumberFormData(Parcel in) {
        number_form = in.readString();
    }

    @Generated(hash = 1605526532)
    public ActivationNumberFormData(String number_form) {
        this.number_form = number_form;
    }

    @Generated(hash = 745731905)
    public ActivationNumberFormData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number_form);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNumber_form() {
        return this.number_form;
    }

    public void setNumber_form(String number_form) {
        this.number_form = number_form;
    }

    public static final Creator<ActivationNumberFormData> CREATOR = new Creator<ActivationNumberFormData>() {
        @Override
        public ActivationNumberFormData createFromParcel(Parcel in) {
            return new ActivationNumberFormData(in);
        }

        @Override
        public ActivationNumberFormData[] newArray(int size) {
            return new ActivationNumberFormData[size];
        }
    };
}
