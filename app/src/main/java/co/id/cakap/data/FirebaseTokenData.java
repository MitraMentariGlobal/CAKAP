package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class FirebaseTokenData implements Parcelable {
    @SerializedName("fcm_token")
    private String fcmToken;

    public FirebaseTokenData() {

    }

    protected FirebaseTokenData(Parcel in) {
        fcmToken = in.readString();
    }

    @Generated(hash = 2063120048)
    public FirebaseTokenData(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fcmToken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getFcmToken() {
        return this.fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public static final Creator<FirebaseTokenData> CREATOR = new Creator<FirebaseTokenData>() {
        @Override
        public FirebaseTokenData createFromParcel(Parcel in) {
            return new FirebaseTokenData(in);
        }

        @Override
        public FirebaseTokenData[] newArray(int size) {
            return new FirebaseTokenData[size];
        }
    };
}
