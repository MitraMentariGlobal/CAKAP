package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class ReligionData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("agama")
    @Expose
    private String agama;

    @Keep
    public ReligionData(String id, String agama) {
        this.id = id;
        this.agama = agama;
    }

    protected ReligionData(Parcel in) {
        id = in.readString();
        agama = in.readString();
    }

    @Generated(hash = 12576858)
    public ReligionData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(agama);
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

    public String getAgama() {
        return this.agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public static final Creator<ReligionData> CREATOR = new Creator<ReligionData>() {
        @Override
        public ReligionData createFromParcel(Parcel in) {
            return new ReligionData(in);
        }

        @Override
        public ReligionData[] newArray(int size) {
            return new ReligionData[size];
        }
    };
}
