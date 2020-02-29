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
public class AddressDefaultData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("kota_id")
    @Expose
    private String kota_id;

    @SerializedName("namakota")
    @Expose
    private String namakota;

    @SerializedName("no_stc")
    @Expose
    private String no_stc;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("fewalletstc")
    @Expose
    private String fewalletstc;

    @SerializedName("ewallet")
    @Expose
    private String ewallet;

    @SerializedName("alamat")
    @Expose
    private String address;

    @SerializedName("timur")
    @Expose
    private String timur;

    @SerializedName("propinsi")
    @Expose
    private String province;

    @SerializedName("tipe")
    @Expose
    private String tipe;

    @SerializedName("persen")
    @Expose
    private String persen;

    protected AddressDefaultData(Parcel in) {
        id = in.readString();
        kota_id = in.readString();
        namakota = in.readString();
        no_stc = in.readString();
        nama = in.readString();
        fewalletstc = in.readString();
        ewallet = in.readString();
        address = in.readString();
        timur = in.readString();
        province = in.readString();
        tipe = in.readString();
        persen = in.readString();
    }

    @Generated(hash = 1523627865)
    public AddressDefaultData(String id, String kota_id, String namakota, String no_stc, String nama,
            String fewalletstc, String ewallet, String address, String timur, String province,
            String tipe, String persen) {
        this.id = id;
        this.kota_id = kota_id;
        this.namakota = namakota;
        this.no_stc = no_stc;
        this.nama = nama;
        this.fewalletstc = fewalletstc;
        this.ewallet = ewallet;
        this.address = address;
        this.timur = timur;
        this.province = province;
        this.tipe = tipe;
        this.persen = persen;
    }

    @Generated(hash = 1538532467)
    public AddressDefaultData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(kota_id);
        dest.writeString(namakota);
        dest.writeString(no_stc);
        dest.writeString(nama);
        dest.writeString(fewalletstc);
        dest.writeString(ewallet);
        dest.writeString(address);
        dest.writeString(timur);
        dest.writeString(province);
        dest.writeString(tipe);
        dest.writeString(persen);
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

    public String getKota_id() {
        return this.kota_id;
    }

    public void setKota_id(String kota_id) {
        this.kota_id = kota_id;
    }

    public String getNamakota() {
        return this.namakota;
    }

    public void setNamakota(String namakota) {
        this.namakota = namakota;
    }

    public String getNo_stc() {
        return this.no_stc;
    }

    public void setNo_stc(String no_stc) {
        this.no_stc = no_stc;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFewalletstc() {
        return this.fewalletstc;
    }

    public void setFewalletstc(String fewalletstc) {
        this.fewalletstc = fewalletstc;
    }

    public String getEwallet() {
        return this.ewallet;
    }

    public void setEwallet(String ewallet) {
        this.ewallet = ewallet;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimur() {
        return this.timur;
    }

    public void setTimur(String timur) {
        this.timur = timur;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTipe() {
        return this.tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPersen() {
        return this.persen;
    }

    public void setPersen(String persen) {
        this.persen = persen;
    }

    public static final Creator<AddressDefaultData> CREATOR = new Creator<AddressDefaultData>() {
        @Override
        public AddressDefaultData createFromParcel(Parcel in) {
            return new AddressDefaultData(in);
        }

        @Override
        public AddressDefaultData[] newArray(int size) {
            return new AddressDefaultData[size];
        }
    };
}
