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
public class EbonusData implements Parcelable {
    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("nominal")
    @Expose
    private String nominal;

    protected EbonusData(Parcel in) {
        created = in.readString();
        description = in.readString();
        nominal = in.readString();
    }

    @Generated(hash = 979334289)
    public EbonusData(String created, String description, String nominal) {
        this.created = created;
        this.description = description;
        this.nominal = nominal;
    }

    @Generated(hash = 1105155169)
    public EbonusData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created);
        dest.writeString(description);
        dest.writeString(nominal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNominal() {
        return this.nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public static final Creator<EbonusData> CREATOR = new Creator<EbonusData>() {
        @Override
        public EbonusData createFromParcel(Parcel in) {
            return new EbonusData(in);
        }

        @Override
        public EbonusData[] newArray(int size) {
            return new EbonusData[size];
        }
    };
}
