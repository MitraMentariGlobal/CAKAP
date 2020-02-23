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
public class ActivityInvToMbData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_id;

    @SerializedName("inv")
    @Expose
    private String transaction_id;

    @SerializedName("no_stc")
    @Expose
    private String member_id;

    @SerializedName("namastc")
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

    @SerializedName("flag_acform")
    @Expose
    private String flag_acform;

    @SerializedName("flag_cancel")
    @Expose
    private String flag_cancel;

    @SerializedName("type_id")
    @Expose
    private String type_id;

    protected ActivityInvToMbData(Parcel in) {
        item_id = in.readString();
        transaction_id = in.readString();
        member_id = in.readString();
        name = in.readString();
        total_amount = in.readString();
        total_pv = in.readString();
        date = in.readString();
        remark = in.readString();
        flag_acform = in.readString();
        flag_cancel = in.readString();
        type_id = in.readString();
    }

    @Generated(hash = 484297221)
    public ActivityInvToMbData(String item_id, String transaction_id, String member_id, String name,
            String total_amount, String total_pv, String date, String remark, String flag_acform,
            String flag_cancel, String type_id) {
        this.item_id = item_id;
        this.transaction_id = transaction_id;
        this.member_id = member_id;
        this.name = name;
        this.total_amount = total_amount;
        this.total_pv = total_pv;
        this.date = date;
        this.remark = remark;
        this.flag_acform = flag_acform;
        this.flag_cancel = flag_cancel;
        this.type_id = type_id;
    }

    @Generated(hash = 377002865)
    public ActivityInvToMbData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(transaction_id);
        dest.writeString(member_id);
        dest.writeString(name);
        dest.writeString(total_amount);
        dest.writeString(total_pv);
        dest.writeString(date);
        dest.writeString(remark);
        dest.writeString(flag_acform);
        dest.writeString(flag_cancel);
        dest.writeString(type_id);
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

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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

    public String getFlag_acform() {
        return this.flag_acform;
    }

    public void setFlag_acform(String flag_acform) {
        this.flag_acform = flag_acform;
    }

    public String getFlag_cancel() {
        return this.flag_cancel;
    }

    public void setFlag_cancel(String flag_cancel) {
        this.flag_cancel = flag_cancel;
    }

    public String getType_id() {
        return this.type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public static final Creator<ActivityInvToMbData> CREATOR = new Creator<ActivityInvToMbData>() {
        @Override
        public ActivityInvToMbData createFromParcel(Parcel in) {
            return new ActivityInvToMbData(in);
        }

        @Override
        public ActivityInvToMbData[] newArray(int size) {
            return new ActivityInvToMbData[size];
        }
    };
}
