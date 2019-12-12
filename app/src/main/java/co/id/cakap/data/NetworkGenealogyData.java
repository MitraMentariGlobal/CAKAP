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
public class NetworkGenealogyData implements Parcelable {
    @SerializedName("user_code")
    @Expose
    private String user_code;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @Keep
    public NetworkGenealogyData(String user_code, String user_name) {
        this.user_code = user_code;
        this.user_name = user_name;
    }

    protected NetworkGenealogyData(Parcel in) {
        user_code = in.readString();
        user_name = in.readString();
    }

    @Generated(hash = 538777502)
    public NetworkGenealogyData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_code);
        dest.writeString(user_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public static final Creator<NetworkGenealogyData> CREATOR = new Creator<NetworkGenealogyData>() {
        @Override
        public NetworkGenealogyData createFromParcel(Parcel in) {
            return new NetworkGenealogyData(in);
        }

        @Override
        public NetworkGenealogyData[] newArray(int size) {
            return new NetworkGenealogyData[size];
        }
    };
}
