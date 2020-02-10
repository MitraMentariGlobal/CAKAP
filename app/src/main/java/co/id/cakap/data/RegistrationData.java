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
public class RegistrationData implements Parcelable {
    @SerializedName("variant_of_kit")
    @Expose
    private String variant_of_kit;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("activation_code")
    @Expose
    private String activation_code;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("status")
    @Expose
    private String status;

    @Keep
    public RegistrationData(String variant_of_kit, String member_id, String activation_code, String date, String status) {
        this.variant_of_kit = variant_of_kit;
        this.member_id = member_id;
        this.activation_code = activation_code;
        this.date = date;
        this.status = status;
    }

    protected RegistrationData(Parcel in) {
        variant_of_kit = in.readString();
        member_id = in.readString();
        activation_code = in.readString();
        date = in.readString();
        status = in.readString();
    }

    @Generated(hash = 26555739)
    public RegistrationData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(variant_of_kit);
        dest.writeString(member_id);
        dest.writeString(activation_code);
        dest.writeString(date);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getVariant_of_kit() {
        return this.variant_of_kit;
    }

    public void setVariant_of_kit(String variant_of_kit) {
        this.variant_of_kit = variant_of_kit;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getActivation_code() {
        return this.activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final Creator<RegistrationData> CREATOR = new Creator<RegistrationData>() {
        @Override
        public RegistrationData createFromParcel(Parcel in) {
            return new RegistrationData(in);
        }

        @Override
        public RegistrationData[] newArray(int size) {
            return new RegistrationData[size];
        }
    };
}
