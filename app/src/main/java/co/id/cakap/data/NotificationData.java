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
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class NotificationData implements Parcelable {
    @SerializedName("notification_title")
    @Expose
    private String notification_title;

    @SerializedName("notification_desc")
    @Expose
    private String notification_desc;

    @SerializedName("date")
    @Expose
    private String date;

    @Keep
    public NotificationData(String notification_title, String notification_desc, String date) {
        this.notification_title = notification_title;
        this.notification_desc = notification_desc;
        this.date = date;
    }

    protected NotificationData(Parcel in) {
        notification_title = in.readString();
        notification_desc = in.readString();
        date = in.readString();
    }

    @Generated(hash = 759109176)
    public NotificationData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notification_title);
        dest.writeString(notification_desc);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
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
