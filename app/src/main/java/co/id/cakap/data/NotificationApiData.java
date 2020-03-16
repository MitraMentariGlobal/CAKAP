package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class NotificationApiData implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("tittle")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("tipe")
    @Expose
    private String tipe;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("created")
    @Expose
    private String created;

    protected NotificationApiData(Parcel in) {
        id = in.readString();
        title = in.readString();
        body = in.readString();
        member_id = in.readString();
        tipe = in.readString();
        status = in.readString();
        created = in.readString();
    }

    @Generated(hash = 2073031314)
    public NotificationApiData(String id, String title, String body, String member_id, String tipe,
            String status, String created) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.member_id = member_id;
        this.tipe = tipe;
        this.status = status;
        this.created = created;
    }

    @Generated(hash = 832999423)
    public NotificationApiData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(member_id);
        dest.writeString(tipe);
        dest.writeString(status);
        dest.writeString(created);
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTipe() {
        return this.tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public static final Creator<NotificationApiData> CREATOR = new Creator<NotificationApiData>() {
        @Override
        public NotificationApiData createFromParcel(Parcel in) {
            return new NotificationApiData(in);
        }

        @Override
        public NotificationApiData[] newArray(int size) {
            return new NotificationApiData[size];
        }
    };
}
