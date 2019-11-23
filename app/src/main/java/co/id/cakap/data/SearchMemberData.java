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
public class SearchMemberData implements Parcelable {
    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("jenjang")
    @Expose
    private String jenjang;

    @SerializedName("nama_member")
    @Expose
    private String nama_member;

    @SerializedName("ktp")
    @Expose
    private String ktp;

    @SerializedName("kota")
    @Expose
    private String kota;

    @SerializedName("provinsi")
    @Expose
    private String provinsi;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("sponsor_id")
    @Expose
    private String sponsor_id;

    @SerializedName("sponsor_name")
    @Expose
    private String sponsor_name;

    @SerializedName("recruiting_id")
    @Expose
    private String recruiting_id;

    @SerializedName("recruiting_name")
    @Expose
    private String recruiting_name;

    @Keep
    public SearchMemberData(
            String member_id,
            String jenjang,
            String nama_member,
            String ktp,
            String kota,
            String provinsi,
            String status,
            String sponsor_id,
            String sponsor_name,
            String recruiting_id,
            String recruiting_name
    ) {
        this.member_id = member_id;
        this.jenjang = jenjang;
        this.nama_member = nama_member;
        this.ktp = ktp;
        this.kota = kota;
        this.provinsi = provinsi;
        this.status = status;
        this.sponsor_id = sponsor_id;
        this.sponsor_name = sponsor_name;
        this.recruiting_id = recruiting_id;
        this.recruiting_name = recruiting_name;
    }

    protected SearchMemberData(Parcel in) {
        member_id = in.readString();
        jenjang = in.readString();
        nama_member = in.readString();
        ktp = in.readString();
        kota = in.readString();
        provinsi = in.readString();
        status = in.readString();
        sponsor_id = in.readString();
        sponsor_name = in.readString();
        recruiting_id = in.readString();
        recruiting_name = in.readString();
    }

    @Generated(hash = 905515841)
    public SearchMemberData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(member_id);
        dest.writeString(jenjang);
        dest.writeString(nama_member);
        dest.writeString(ktp);
        dest.writeString(kota);
        dest.writeString(provinsi);
        dest.writeString(status);
        dest.writeString(sponsor_id);
        dest.writeString(sponsor_name);
        dest.writeString(recruiting_id);
        dest.writeString(recruiting_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getJenjang() {
        return this.jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getNama_member() {
        return this.nama_member;
    }

    public void setNama_member(String nama_member) {
        this.nama_member = nama_member;
    }

    public String getKtp() {
        return this.ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getKota() {
        return this.kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return this.provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSponsor_id() {
        return this.sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getSponsor_name() {
        return this.sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getRecruiting_id() {
        return this.recruiting_id;
    }

    public void setRecruiting_id(String recruiting_id) {
        this.recruiting_id = recruiting_id;
    }

    public String getRecruiting_name() {
        return this.recruiting_name;
    }

    public void setRecruiting_name(String recruiting_name) {
        this.recruiting_name = recruiting_name;
    }

    public static final Creator<SearchMemberData> CREATOR = new Creator<SearchMemberData>() {
        @Override
        public SearchMemberData createFromParcel(Parcel in) {
            return new SearchMemberData(in);
        }

        @Override
        public SearchMemberData[] newArray(int size) {
            return new SearchMemberData[size];
        }
    };
}
