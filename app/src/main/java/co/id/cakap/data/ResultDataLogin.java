package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ResultDataLogin implements Parcelable {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("session_token")
    @Expose
    private String session_token;

    protected ResultDataLogin(Parcel in) {
        url = in.readString();
        role = in.readString();
        session_token = in.readString();
    }

    @Generated(hash = 1795545469)
    public ResultDataLogin(String url, String role, String session_token) {
        this.url = url;
        this.role = role;
        this.session_token = session_token;
    }

    @Generated(hash = 820616836)
    public ResultDataLogin() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(role);
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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSession_token() {
        return this.session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public static final Creator<ResultDataLogin> CREATOR = new Creator<ResultDataLogin>() {
        @Override
        public ResultDataLogin createFromParcel(Parcel in) {
            return new ResultDataLogin(in);
        }

        @Override
        public ResultDataLogin[] newArray(int size) {
            return new ResultDataLogin[size];
        }
    };
}
