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
public class BankData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @Keep
    public BankData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected BankData(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Generated(hash = 924173817)
    public BankData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<BankData> CREATOR = new Creator<BankData>() {
        @Override
        public BankData createFromParcel(Parcel in) {
            return new BankData(in);
        }

        @Override
        public BankData[] newArray(int size) {
            return new BankData[size];
        }
    };
}
