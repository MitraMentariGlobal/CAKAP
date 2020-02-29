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
public class ActivityReqInvMbData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_id;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("ftotalharga")
    @Expose
    private String total_amount;

    @SerializedName("ftotalpv")
    @Expose
    private String total_pv;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("remarkapp")
    @Expose
    private String remark;

    protected ActivityReqInvMbData(Parcel in) {
        item_id = in.readString();
        member_id = in.readString();
        name = in.readString();
        total_amount = in.readString();
        total_pv = in.readString();
        date = in.readString();
        remark = in.readString();
    }

    @Generated(hash = 1489480938)
    public ActivityReqInvMbData(String item_id, String member_id, String name, String total_amount,
            String total_pv, String date, String remark) {
        this.item_id = item_id;
        this.member_id = member_id;
        this.name = name;
        this.total_amount = total_amount;
        this.total_pv = total_pv;
        this.date = date;
        this.remark = remark;
    }

    @Generated(hash = 1294999872)
    public ActivityReqInvMbData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(member_id);
        dest.writeString(name);
        dest.writeString(total_amount);
        dest.writeString(total_pv);
        dest.writeString(date);
        dest.writeString(remark);
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

    public String getTotal_amount() {
        return this.total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_pv() {
        return this.total_pv;
    }

    public void setTotal_pv(String total_pv) {
        this.total_pv = total_pv;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static final Creator<ActivityReqInvMbData> CREATOR = new Creator<ActivityReqInvMbData>() {
        @Override
        public ActivityReqInvMbData createFromParcel(Parcel in) {
            return new ActivityReqInvMbData(in);
        }

        @Override
        public ActivityReqInvMbData[] newArray(int size) {
            return new ActivityReqInvMbData[size];
        }
    };
}
