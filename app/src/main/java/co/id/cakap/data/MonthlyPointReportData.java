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
    @SerializedName("distr_id")
    @Expose
    private String distr_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("ppv")
    @Expose
    private String ppv;

    @SerializedName("ngpv")
    @Expose
    private String ngpv;

    @SerializedName("tgpv")
    @Expose
    private String tgpv;

    @SerializedName("status")
    @Expose
    private String status;

    @Keep
    public MonthlyPointReportData(String distr_id, String name, String rank, String ppv, String ngpv, String tgpv, String status) {
        this.distr_id = distr_id;
        this.name = name;
        this.rank = rank;
        this.ppv = ppv;
        this.ngpv = ngpv;
        this.tgpv = tgpv;
        this.status = status;
    }

    protected MonthlyPointReportData(Parcel in) {
        distr_id = in.readString();
        name = in.readString();
        rank = in.readString();
        ppv = in.readString();
        ngpv = in.readString();
        tgpv = in.readString();
        status = in.readString();
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
