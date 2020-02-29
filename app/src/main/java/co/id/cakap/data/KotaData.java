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
public class KotaData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    protected KotaData(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Generated(hash = 1853584925)
    public KotaData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1291959272)
    public KotaData() {
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

    public static final Creator<KotaData> CREATOR = new Creator<KotaData>() {
        @Override
        public KotaData createFromParcel(Parcel in) {
            return new KotaData(in);
        }

        @Override
        public KotaData[] newArray(int size) {
            return new KotaData[size];
        }
    };
}
