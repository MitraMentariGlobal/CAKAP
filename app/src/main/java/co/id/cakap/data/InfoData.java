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
public class InfoData implements Parcelable {
    @SerializedName("txt")
    @Expose
    private String txt;

    @SerializedName("value")
    @Expose
    private String value;

    protected InfoData(Parcel in) {
        txt = in.readString();
        value = in.readString();
    }

    @Generated(hash = 1479398009)
    public InfoData(String txt, String value) {
        this.txt = txt;
        this.value = value;
    }

    @Generated(hash = 1435289617)
    public InfoData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(txt);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final Creator<InfoData> CREATOR = new Creator<InfoData>() {
        @Override
        public InfoData createFromParcel(Parcel in) {
            return new InfoData(in);
        }

        @Override
        public InfoData[] newArray(int size) {
            return new InfoData[size];
        }
    };
}
