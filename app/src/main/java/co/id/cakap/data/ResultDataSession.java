package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class ResultDataSession implements Parcelable {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("session_token")
    @Expose
    private String session_token;

    protected ResultDataSession(Parcel in) {
        url = in.readString();
        session_token = in.readString();
    }

    @Generated(hash = 1909166011)
    public ResultDataSession(String url, String session_token) {
        this.url = url;
        this.session_token = session_token;
    }

    @Generated(hash = 2065038179)
    public ResultDataSession() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(session_token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSession_token() {
        return this.session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public static final Creator<ResultDataSession> CREATOR = new Creator<ResultDataSession>() {
        @Override
        public ResultDataSession createFromParcel(Parcel in) {
            return new ResultDataSession(in);
        }

        @Override
        public ResultDataSession[] newArray(int size) {
            return new ResultDataSession[size];
        }
    };
}
