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
public class LevelData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("level")
    @Expose
    private String level;

    protected LevelData(Parcel in) {
        id = in.readString();
        level = in.readString();
    }

    @Generated(hash = 984786236)
    public LevelData(String id, String level) {
        this.id = id;
        this.level = level;
    }

    @Generated(hash = 1583901635)
    public LevelData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(level);
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

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static final Creator<LevelData> CREATOR = new Creator<LevelData>() {
        @Override
        public LevelData createFromParcel(Parcel in) {
            return new LevelData(in);
        }

        @Override
        public LevelData[] newArray(int size) {
            return new LevelData[size];
        }
    };
}
