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
public class ActivityCashbillData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_id;

    @SerializedName("inv")
    @Expose
    private String transaction_id;

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

    @SerializedName("tgl")
    @Expose
    private String date;

    @SerializedName("tgls")
    @Expose
    private String dates;

    @SerializedName("remark")
    @Expose
    private String remark;

    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("kit")
    @Expose
    private String kit;

    protected ActivityCashbillData(Parcel in) {
        item_id = in.readString();
        transaction_id = in.readString();
        member_id = in.readString();
        name = in.readString();
        total_amount = in.readString();
        total_pv = in.readString();
        date = in.readString();
        dates = in.readString();
        remark = in.readString();
        note = in.readString();
        kit = in.readString();
    }

    @Generated(hash = 1909153190)
    public ActivityCashbillData(String item_id, String transaction_id, String member_id, String name,
            String total_amount, String total_pv, String date, String dates, String remark, String note,
            String kit) {
        this.item_id = item_id;
        this.transaction_id = transaction_id;
        this.member_id = member_id;
        this.name = name;
        this.total_amount = total_amount;
        this.total_pv = total_pv;
        this.date = date;
        this.dates = dates;
        this.remark = remark;
        this.note = note;
        this.kit = kit;
    }

    @Generated(hash = 998928684)
    public ActivityCashbillData() {
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
        dest.writeString(dates);
        dest.writeString(remark);
        dest.writeString(note);
        dest.writeString(kit);
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

    public String getDates() {
        return this.dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKit() {
        return this.kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public static final Creator<ActivityCashbillData> CREATOR = new Creator<ActivityCashbillData>() {
        @Override
        public ActivityCashbillData createFromParcel(Parcel in) {
            return new ActivityCashbillData(in);
        }

        @Override
        public ActivityCashbillData[] newArray(int size) {
            return new ActivityCashbillData[size];
        }
    };
}
