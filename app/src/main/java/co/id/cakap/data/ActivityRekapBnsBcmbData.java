package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class ActivityRekapBnsBcmbData implements Parcelable {
    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mobile_phone")
    @Expose
    private String mobile_phone;

    @SerializedName("amount")
    @Expose
    private String amount;

    @Keep
    public ActivityRekapBnsBcmbData(String member_id, String name, String mobile_phone, String amount) {
        this.member_id = member_id;
        this.name = name;
        this.mobile_phone = mobile_phone;
        this.amount = amount;
    }

    protected ActivityRekapBnsBcmbData(Parcel in) {
        member_id = in.readString();
        name = in.readString();
        mobile_phone = in.readString();
        amount = in.readString();
    }

    @Generated(hash = 1219341017)
    public ActivityRekapBnsBcmbData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(member_id);
        dest.writeString(name);
        dest.writeString(mobile_phone);
        dest.writeString(amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_phone() {
        return this.mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static final Creator<ActivityRekapBnsBcmbData> CREATOR = new Creator<ActivityRekapBnsBcmbData>() {
        @Override
        public ActivityRekapBnsBcmbData createFromParcel(Parcel in) {
            return new ActivityRekapBnsBcmbData(in);
        }

        @Override
        public ActivityRekapBnsBcmbData[] newArray(int size) {
            return new ActivityRekapBnsBcmbData[size];
        }
    };
}
