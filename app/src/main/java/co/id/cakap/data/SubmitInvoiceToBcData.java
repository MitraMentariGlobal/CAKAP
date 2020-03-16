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
public class SubmitInvoiceToBcData extends SubmitInvoiceToBcParentData implements Parcelable {

    @SerializedName("inv")
    @Expose
    private String inv;

    @SerializedName("tgl")
    @Expose
    private String tgl;

    @SerializedName("mbid")
    @Expose
    private String member_id;

    @SerializedName("no_stc")
    @Expose
    private String no_stc;

    @SerializedName("namastc")
    @Expose
    private String namastc;

    @SerializedName("remark")
    @Expose
    private String remark;

    @SerializedName("totalharga")
    @Expose
    private String totalharga;

    protected SubmitInvoiceToBcData(Parcel in) {
        inv = in.readString();
        tgl = in.readString();
        member_id = in.readString();
        no_stc = in.readString();
        namastc = in.readString();
        remark = in.readString();
        totalharga = in.readString();
    }

    @Generated(hash = 12088810)
    public SubmitInvoiceToBcData(String inv, String tgl, String member_id, String no_stc,
            String namastc, String remark, String totalharga) {
        this.inv = inv;
        this.tgl = tgl;
        this.member_id = member_id;
        this.no_stc = no_stc;
        this.namastc = namastc;
        this.remark = remark;
        this.totalharga = totalharga;
    }

    @Generated(hash = 852363003)
    public SubmitInvoiceToBcData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(inv);
        dest.writeString(tgl);
        dest.writeString(member_id);
        dest.writeString(no_stc);
        dest.writeString(namastc);
        dest.writeString(remark);
        dest.writeString(totalharga);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getInv() {
        return this.inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getTgl() {
        return this.tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getNo_stc() {
        return this.no_stc;
    }

    public void setNo_stc(String no_stc) {
        this.no_stc = no_stc;
    }

    public String getNamastc() {
        return this.namastc;
    }

    public void setNamastc(String namastc) {
        this.namastc = namastc;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalharga() {
        return this.totalharga;
    }

    public void setTotalharga(String totalharga) {
        this.totalharga = totalharga;
    }

    public static final Creator<SubmitInvoiceToBcData> CREATOR = new Creator<SubmitInvoiceToBcData>() {
        @Override
        public SubmitInvoiceToBcData createFromParcel(Parcel in) {
            return new SubmitInvoiceToBcData(in);
        }

        @Override
        public SubmitInvoiceToBcData[] newArray(int size) {
            return new SubmitInvoiceToBcData[size];
        }
    };
}
