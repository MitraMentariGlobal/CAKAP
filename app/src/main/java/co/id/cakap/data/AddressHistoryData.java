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
public class AddressHistoryData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("kota")
    @Expose
    private String kota;

    @SerializedName("kota_id")
    @Expose
    private String kota_id;

    @SerializedName("alamat")
    @Expose
    private String address;

    @SerializedName("provinsi")
    @Expose
    private String province;

    @SerializedName("timur")
    @Expose
    private String timur;

    protected AddressHistoryData(Parcel in) {
        id = in.readString();
        kota = in.readString();
        kota_id = in.readString();
        address = in.readString();
        province = in.readString();
        timur = in.readString();
    }

    @Generated(hash = 2108295997)
    public AddressHistoryData(String id, String kota, String kota_id, String address, String province,
            String timur) {
        this.id = id;
        this.kota = kota;
        this.kota_id = kota_id;
        this.address = address;
        this.province = province;
        this.timur = timur;
    }

    @Generated(hash = 2095842438)
    public AddressHistoryData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(kota);
        dest.writeString(kota_id);
        dest.writeString(address);
        dest.writeString(province);
        dest.writeString(timur);
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

    public String getKota() {
        return this.kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKota_id() {
        return this.kota_id;
    }

    public void setKota_id(String kota_id) {
        this.kota_id = kota_id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTimur() {
        return this.timur;
    }

    public void setTimur(String timur) {
        this.timur = timur;
    }

    public static final Creator<AddressHistoryData> CREATOR = new Creator<AddressHistoryData>() {
        @Override
        public AddressHistoryData createFromParcel(Parcel in) {
            return new AddressHistoryData(in);
        }

        @Override
        public AddressHistoryData[] newArray(int size) {
            return new AddressHistoryData[size];
        }
    };
}
