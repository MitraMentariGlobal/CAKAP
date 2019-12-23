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
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class DownlineListingData implements Parcelable {
    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("no_hp")
    @Expose
    private String no_hp;

    @SerializedName("join_date")
    @Expose
    private String join_date;

    @SerializedName("status")
    @Expose
    private String status;

    @Keep
    public DownlineListingData(String member_id, String name, String no_hp, String join_date, String status) {
        this.member_id = member_id;
        this.name = name;
        this.no_hp = no_hp;
        this.join_date = join_date;
        this.status = status;
    }

    protected DownlineListingData(Parcel in) {
        member_id = in.readString();
        name = in.readString();
        no_hp = in.readString();
        join_date = in.readString();
        status = in.readString();
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
