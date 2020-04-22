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
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ActivityRekapBnsBcmbData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_id;

    @SerializedName("_member_id")
    @Expose
    private String member_id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("_hp")
    @Expose
    private String mobile_phone;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("tgl_terima")
    @Expose
    private String date;

    protected ActivityRekapBnsBcmbData(Parcel in) {
        item_id = in.readString();
        member_id = in.readString();
        name = in.readString();
        mobile_phone = in.readString();
        amount = in.readString();
        flag = in.readString();
        date = in.readString();
    }

    @Generated(hash = 1549123982)
    public ActivityRekapBnsBcmbData(String item_id, String member_id, String name, String mobile_phone,
            String amount, String flag, String date) {
        this.item_id = item_id;
        this.member_id = member_id;
        this.name = name;
        this.mobile_phone = mobile_phone;
        this.amount = amount;
        this.flag = flag;
        this.date = date;
    }

    @Generated(hash = 1219341017)
    public ActivityRekapBnsBcmbData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(member_id);
        dest.writeString(name);
        dest.writeString(mobile_phone);
        dest.writeString(amount);
        dest.writeString(flag);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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
