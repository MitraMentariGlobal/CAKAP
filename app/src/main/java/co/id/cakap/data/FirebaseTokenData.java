package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

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
public class FirebaseTokenData implements Parcelable {
    @SerializedName("fcm_token")
    private String fcmToken;

    @SerializedName("firebase_user")
    private String firebase_user;

    public FirebaseTokenData() {

    }

    protected FirebaseTokenData(Parcel in) {
        fcmToken = in.readString();
        firebase_user = in.readString();
    }

    @Generated(hash = 90837919)
    public FirebaseTokenData(String fcmToken, String firebase_user) {
        this.fcmToken = fcmToken;
        this.firebase_user = firebase_user;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fcmToken);
        dest.writeString(firebase_user);
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

    public String getFirebase_user() {
        return this.firebase_user;
    }

    public void setFirebase_user(String firebase_user) {
        this.firebase_user = firebase_user;
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
