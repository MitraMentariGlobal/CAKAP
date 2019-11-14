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
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class AddressData implements Parcelable {
    @SerializedName("kota")
    @Expose
    private String kota;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("address")
    @Expose
    private String address;

    @Keep
    public AddressData(String kota, String province, String address) {
        this.kota = kota;
        this.province = province;
        this.address = address;
    }

    protected AddressData(Parcel in) {
        kota = in.readString();
        province = in.readString();
        address = in.readString();
    }

    @Generated(hash = 262205491)
    public AddressData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kota);
        dest.writeString(province);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getKota() {
        return this.kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static final Creator<AddressData> CREATOR = new Creator<AddressData>() {
        @Override
        public AddressData createFromParcel(Parcel in) {
            return new AddressData(in);
        }

        @Override
        public AddressData[] newArray(int size) {
            return new AddressData[size];
        }
    };
}
