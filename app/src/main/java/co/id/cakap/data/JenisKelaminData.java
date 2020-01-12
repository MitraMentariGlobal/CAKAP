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
public class JenisKelaminData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @Keep
    public JenisKelaminData(String id) {
        this.id = id;
    }

    protected JenisKelaminData(Parcel in) {
        id = in.readString();
    }

    @Generated(hash = 1761805555)
    public JenisKelaminData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
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

    public static final Creator<JenisKelaminData> CREATOR = new Creator<JenisKelaminData>() {
        @Override
        public JenisKelaminData createFromParcel(Parcel in) {
            return new JenisKelaminData(in);
        }

        @Override
        public JenisKelaminData[] newArray(int size) {
            return new JenisKelaminData[size];
        }
    };
}
