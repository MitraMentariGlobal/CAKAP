package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class SubmitCashbillData extends SubmitCashbillParentData implements Parcelable {
    @SerializedName("inv")
    @Expose
    private String inv;

    @SerializedName("tgl")
    @Expose
    private String tgl;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("no_stc")
    @Expose
    private String no_stc;

    @SerializedName("remark")
    @Expose
    private String remark;

    @SerializedName("totalharga")
    @Expose
    private String totalharga;

    protected SubmitCashbillData(Parcel in) {
        inv = in.readString();
        tgl = in.readString();
        member_id = in.readString();
        no_stc = in.readString();
        remark = in.readString();
        totalharga = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(inv);
        dest.writeString(tgl);
        dest.writeString(member_id);
        dest.writeString(no_stc);
        dest.writeString(remark);
        dest.writeString(totalharga);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubmitCashbillData> CREATOR = new Creator<SubmitCashbillData>() {
        @Override
        public SubmitCashbillData createFromParcel(Parcel in) {
            return new SubmitCashbillData(in);
        }

        @Override
        public SubmitCashbillData[] newArray(int size) {
            return new SubmitCashbillData[size];
        }
    };
}
