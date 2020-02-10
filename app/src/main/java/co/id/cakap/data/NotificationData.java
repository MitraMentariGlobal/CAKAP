package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class NotificationData implements Parcelable {
    @PrimaryKey
    @Id(autoincrement = true)
    Long id;

    @SerializedName("notification_title")
    @Expose
    private String notification_title;

    @SerializedName("notification_desc")
    @Expose
    private String notification_desc;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("is_read")
    @Expose
    private boolean isRead;

    protected NotificationData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        notification_title = in.readString();
        notification_desc = in.readString();
        date = in.readString();
        isRead = in.readByte() != 0;
    }

    @Generated(hash = 1314555022)
    public NotificationData(Long id, String notification_title, String notification_desc, String date,
            boolean isRead) {
        this.id = id;
        this.notification_title = notification_title;
        this.notification_desc = notification_desc;
        this.date = date;
        this.isRead = isRead;
    }

    @Generated(hash = 759109176)
    public NotificationData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(notification_title);
        dest.writeString(notification_desc);
        dest.writeString(date);
        dest.writeByte((byte) (isRead ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotification_title() {
        return this.notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_desc() {
        return this.notification_desc;
    }

    public void setNotification_desc(String notification_desc) {
        this.notification_desc = notification_desc;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public static final Creator<NotificationData> CREATOR = new Creator<NotificationData>() {
        @Override
        public NotificationData createFromParcel(Parcel in) {
            return new NotificationData(in);
        }

        @Override
        public NotificationData[] newArray(int size) {
            return new NotificationData[size];
        }
    };
}
