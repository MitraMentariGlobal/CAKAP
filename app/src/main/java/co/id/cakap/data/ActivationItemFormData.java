package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ActivationItemFormData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("item_id")
    @Expose
    private String item_id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    protected ActivationItemFormData(Parcel in) {
        id = in.readString();
        code = in.readString();
        item_id = in.readString();
        status = in.readString();
        member_id = in.readString();
    }

    @Generated(hash = 1467345256)
    public ActivationItemFormData(String id, String code, String item_id, String status, String member_id) {
        this.id = id;
        this.code = code;
        this.item_id = item_id;
        this.status = status;
        this.member_id = member_id;
    }

    @Generated(hash = 561910474)
    public ActivationItemFormData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(code);
        dest.writeString(item_id);
        dest.writeString(status);
        dest.writeString(member_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public static final Creator<ActivationItemFormData> CREATOR = new Creator<ActivationItemFormData>() {
        @Override
        public ActivationItemFormData createFromParcel(Parcel in) {
            return new ActivationItemFormData(in);
        }

        @Override
        public ActivationItemFormData[] newArray(int size) {
            return new ActivationItemFormData[size];
        }
    };
}
