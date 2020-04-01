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
public class MonthlyPointReportData implements Parcelable {
    @SerializedName("member_id")
    @Expose
    private String distr_id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("PPV")
    @Expose
    private String ppv;

    @SerializedName("NGPV")
    @Expose
    private String ngpv;

    @SerializedName("TGPV")
    @Expose
    private String tgpv;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("ka1")
    @Expose
    private String ka1;

    @SerializedName("ka2")
    @Expose
    private String ka2;

    @SerializedName("ka3")
    @Expose
    private String ka3;

    @SerializedName("str1")
    @Expose
    private String str1;

    @SerializedName("str2")
    @Expose
    private String str2;

    @SerializedName("str3")
    @Expose
    private String str3;

    protected MonthlyPointReportData(Parcel in) {
        distr_id = in.readString();
        name = in.readString();
        rank = in.readString();
        ppv = in.readString();
        ngpv = in.readString();
        tgpv = in.readString();
        status = in.readString();
        ka1 = in.readString();
        ka2 = in.readString();
        ka3 = in.readString();
        str1 = in.readString();
        str2 = in.readString();
        str3 = in.readString();
    }

    @Generated(hash = 577384524)
    public MonthlyPointReportData(String distr_id, String name, String rank, String ppv, String ngpv, String tgpv,
            String status, String ka1, String ka2, String ka3, String str1, String str2, String str3) {
        this.distr_id = distr_id;
        this.name = name;
        this.rank = rank;
        this.ppv = ppv;
        this.ngpv = ngpv;
        this.tgpv = tgpv;
        this.status = status;
        this.ka1 = ka1;
        this.ka2 = ka2;
        this.ka3 = ka3;
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }

    @Generated(hash = 1872705719)
    public MonthlyPointReportData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(distr_id);
        dest.writeString(name);
        dest.writeString(rank);
        dest.writeString(ppv);
        dest.writeString(ngpv);
        dest.writeString(tgpv);
        dest.writeString(status);
        dest.writeString(ka1);
        dest.writeString(ka2);
        dest.writeString(ka3);
        dest.writeString(str1);
        dest.writeString(str2);
        dest.writeString(str3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDistr_id() {
        return this.distr_id;
    }

    public void setDistr_id(String distr_id) {
        this.distr_id = distr_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPpv() {
        return this.ppv;
    }

    public void setPpv(String ppv) {
        this.ppv = ppv;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKa1() {
        return this.ka1;
    }

    public void setKa1(String ka1) {
        this.ka1 = ka1;
    }

    public String getKa2() {
        return this.ka2;
    }

    public void setKa2(String ka2) {
        this.ka2 = ka2;
    }

    public String getKa3() {
        return this.ka3;
    }

    public void setKa3(String ka3) {
        this.ka3 = ka3;
    }

    public String getStr1() {
        return this.str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return this.str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return this.str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public static final Creator<MonthlyPointReportData> CREATOR = new Creator<MonthlyPointReportData>() {
        @Override
        public MonthlyPointReportData createFromParcel(Parcel in) {
            return new MonthlyPointReportData(in);
        }

        @Override
        public MonthlyPointReportData[] newArray(int size) {
            return new MonthlyPointReportData[size];
        }
    };
}
