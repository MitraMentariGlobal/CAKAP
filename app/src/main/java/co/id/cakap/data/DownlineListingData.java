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
public class DownlineListingData implements Parcelable {
    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("hp")
    @Expose
    private String no_hp;

    @SerializedName("joindate")
    @Expose
    private String join_date;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("tgpv")
    @Expose
    private String tgpv;

    @SerializedName("atgpv")
    @Expose
    private String atgpv;

    protected DownlineListingData(Parcel in) {
        member_id = in.readString();
        name = in.readString();
        no_hp = in.readString();
        join_date = in.readString();
        status = in.readString();
        tgpv = in.readString();
        atgpv = in.readString();
    }

    @Generated(hash = 1622743551)
    public DownlineListingData(String member_id, String name, String no_hp, String join_date,
            String status, String tgpv, String atgpv) {
        this.member_id = member_id;
        this.name = name;
        this.no_hp = no_hp;
        this.join_date = join_date;
        this.status = status;
        this.tgpv = tgpv;
        this.atgpv = atgpv;
    }

    @Generated(hash = 1919124717)
    public DownlineListingData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(member_id);
        dest.writeString(name);
        dest.writeString(no_hp);
        dest.writeString(join_date);
        dest.writeString(status);
        dest.writeString(tgpv);
        dest.writeString(atgpv);
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

    public String getNo_hp() {
        return this.no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getJoin_date() {
        return this.join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public static final Creator<DownlineListingData> CREATOR = new Creator<DownlineListingData>() {
        @Override
        public DownlineListingData createFromParcel(Parcel in) {
            return new DownlineListingData(in);
        }

        @Override
        public DownlineListingData[] newArray(int size) {
            return new DownlineListingData[size];
        }
    };
}
