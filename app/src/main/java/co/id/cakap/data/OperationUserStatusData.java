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
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class OperationUserStatusData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String user_code;

    @SerializedName("nama")
    @Expose
    private String user_name;

    @SerializedName("status")
    @Expose
    private String status;

    @Keep
    public OperationUserStatusData(String user_code, String user_name, String status) {
        this.user_code = user_code;
        this.user_name = user_name;
        this.status = status;
    }

    protected OperationUserStatusData(Parcel in) {
        user_code = in.readString();
        user_name = in.readString();
        status = in.readString();
    }

    @Generated(hash = 1640796744)
    public OperationUserStatusData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_code);
        dest.writeString(user_name);
        dest.writeString(status);
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final Creator<OperationUserStatusData> CREATOR = new Creator<OperationUserStatusData>() {
        @Override
        public OperationUserStatusData createFromParcel(Parcel in) {
            return new OperationUserStatusData(in);
        }

        @Override
        public OperationUserStatusData[] newArray(int size) {
            return new OperationUserStatusData[size];
        }
    };
}
