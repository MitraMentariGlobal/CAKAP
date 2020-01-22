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
public class ActivityBonusStatementData implements Parcelable {
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("file_name")
    @Expose
    private String file_name;

    protected ActivityBonusStatementData(Parcel in) {
        link = in.readString();
        file_name = in.readString();
    }

    @Generated(hash = 1523442060)
    public ActivityBonusStatementData(String link, String file_name) {
        this.link = link;
        this.file_name = file_name;
    }

    @Generated(hash = 498489775)
    public ActivityBonusStatementData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(link);
        dest.writeString(file_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFile_name() {
        return this.file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public static final Creator<ActivityBonusStatementData> CREATOR = new Creator<ActivityBonusStatementData>() {
        @Override
        public ActivityBonusStatementData createFromParcel(Parcel in) {
            return new ActivityBonusStatementData(in);
        }

        @Override
        public ActivityBonusStatementData[] newArray(int size) {
            return new ActivityBonusStatementData[size];
        }
    };
}
