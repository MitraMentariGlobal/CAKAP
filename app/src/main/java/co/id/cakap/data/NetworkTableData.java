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
public class NetworkTableData implements Parcelable {
    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("name_member")
    @Expose
    private String name_member;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("upline")
    @Expose
    private String upline;

    @SerializedName("posisi")
    @Expose
    private String posisi;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("tupo")
    @Expose
    private String tupo;

    @SerializedName("ppv")
    @Expose
    private String ppv;

    @SerializedName("ppvreg")
    @Expose
    private String ppvreg;

    @SerializedName("ngpv")
    @Expose
    private String ngpv;

    @SerializedName("tgpv")
    @Expose
    private String tgpv;

    @SerializedName("atgpv")
    @Expose
    private String atgpv;

    @Keep
    public NetworkTableData(String level, String member_id, String name_member, String province, String city, String upline, String posisi, String status, String tupo, String ppv, String ppvreg, String ngpv, String tgpv, String atgpv) {
        this.level = level;
        this.member_id = member_id;
        this.name_member = name_member;
        this.province = province;
        this.city = city;
        this.upline = upline;
        this.posisi = posisi;
        this.status = status;
        this.tupo = tupo;
        this.ppv = ppv;
        this.ppvreg = ppvreg;
        this.ngpv = ngpv;
        this.tgpv = tgpv;
        this.atgpv = atgpv;
    }

    protected NetworkTableData(Parcel in) {
        level = in.readString();
        member_id = in.readString();
        name_member = in.readString();
        province = in.readString();
        city = in.readString();
        upline = in.readString();
        posisi = in.readString();
        status = in.readString();
        tupo = in.readString();
        ppv = in.readString();
        ppvreg = in.readString();
        ngpv = in.readString();
        tgpv = in.readString();
        atgpv = in.readString();
    }

    @Generated(hash = 1402856885)
    public NetworkTableData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(level);
        dest.writeString(member_id);
        dest.writeString(name_member);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(upline);
        dest.writeString(posisi);
        dest.writeString(status);
        dest.writeString(tupo);
        dest.writeString(ppv);
        dest.writeString(ppvreg);
        dest.writeString(ngpv);
        dest.writeString(tgpv);
        dest.writeString(atgpv);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName_member() {
        return this.name_member;
    }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpline() {
        return this.upline;
    }

    public void setUpline(String upline) {
        this.upline = upline;
    }

    public String getPosisi() {
        return this.posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTupo() {
        return this.tupo;
    }

    public void setTupo(String tupo) {
        this.tupo = tupo;
    }

    public String getPpv() {
        return this.ppv;
    }

    public void setPpv(String ppv) {
        this.ppv = ppv;
    }

    public String getPpvreg() {
        return this.ppvreg;
    }

    public void setPpvreg(String ppvreg) {
        this.ppvreg = ppvreg;
    }

    public String getNgpv() {
        return this.ngpv;
    }

    public void setNgpv(String ngpv) {
        this.ngpv = ngpv;
    }

    public String getTgpv() {
        return this.tgpv;
    }

    public void setTgpv(String tgpv) {
        this.tgpv = tgpv;
    }

    public String getAtgpv() {
        return this.atgpv;
    }

    public void setAtgpv(String atgpv) {
        this.atgpv = atgpv;
    }

    public static final Creator<NetworkTableData> CREATOR = new Creator<NetworkTableData>() {
        @Override
        public NetworkTableData createFromParcel(Parcel in) {
            return new NetworkTableData(in);
        }

        @Override
        public NetworkTableData[] newArray(int size) {
            return new NetworkTableData[size];
        }
    };
}
