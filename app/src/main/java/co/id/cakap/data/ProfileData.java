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
public class ProfileData implements Parcelable {
    @SerializedName("joindate")
    @Expose
    private String tanggal_daftar;

    @SerializedName("expired")
    @Expose
    private String tanggal_expired;

    @SerializedName("no_stc")
    @Expose
    private String stockist_id;

    @SerializedName("namastc")
    @Expose
    private String stockist_name;

    @SerializedName("enroller_id")
    @Expose
    private String recruiting_id;

    @SerializedName("namaenroller")
    @Expose
    private String recruiting_name;

    @SerializedName("sponsor_id")
    @Expose
    private String sponsor_id;

    @SerializedName("namasponsor")
    @Expose
    private String sponsor_name;

    @SerializedName("id")
    @Expose
    private String member_id;

    @SerializedName("nama")
    @Expose
    private String full_name;

    @SerializedName("noktp")
    @Expose
    private String id_card;

    @SerializedName("jk")
    @Expose
    private String gender;

    @SerializedName("tempatlahir")
    @Expose
    private String pob;

    @SerializedName("ftgllahir")
    @Expose
    private String dob;

    @SerializedName("religion")
    @Expose
    private String religion;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nonpwp")
    @Expose
    private String npwp;

    @SerializedName("office")
    @Expose
    private String pekerjaan;

    @SerializedName("menikah")
    @Expose
    private String status_pernikahan;

    @SerializedName("suami")
    @Expose
    private String suami;

    @SerializedName("anak")
    @Expose
    private String jumlah_anak;

    @SerializedName("telp")
    @Expose
    private String phone_number;

    @SerializedName("fax")
    @Expose
    private String fax;

//    @SerializedName("whatsapp_number")
//    @Expose
//    private String whatsapp_number;

    @SerializedName("hp")
    @Expose
    private String mobile_number;

    @SerializedName("alamat")
    @Expose
    private String address;

    @SerializedName("propinsi")
    @Expose
    private String province;

    @SerializedName("kota")
    @Expose
    private String city;

    @SerializedName("kota_id")
    @Expose
    private String city_id;

    @SerializedName("kodepos")
    @Expose
    private String postal_code;

//    @SerializedName("activation_code")
//    @Expose
//    private String activation_code;

    @SerializedName("ahliwaris")
    @Expose
    private String nama_pewaris;

    @SerializedName("hubungan")
    @Expose
    private String relationship;

    @SerializedName("bank_id")
    @Expose
    private String bank_id;

    @SerializedName("namabank")
    @Expose
    private String bank_name;

    @SerializedName("area")
    @Expose
    private String branch;

    @SerializedName("namanasabah")
    @Expose
    private String account_holder_name;

    @SerializedName("no")
    @Expose
    private String account_number;

    @Keep
    public ProfileData(
            String tanggal_daftar,
            String tanggal_expired,
            String stockist_id,
            String stockist_name,
            String recruiting_id,
            String recruiting_name,
            String sponsor_id,
            String sponsor_name,
            String member_id,
            String full_name,
            String id_card,
            String gender,
            String pob,
            String dob,
            String religion,
            String email,
            String npwp,
            String pekerjaan,
            String status_pernikahan,
            String suami,
            String jumlah_anak,
            String phone_number,
            String fax,
//            String whatsapp_number,
            String mobile_number,
            String address,
            String province,
            String city,
            String city_id,
            String postal_code,
//            String activation_code,
            String nama_pewaris,
            String relationship,
            String bank_id,
            String bank_name,
            String branch,
            String account_holder_name,
            String account_number
    ) {
        this.recruiting_id = recruiting_id;
        this.recruiting_name = recruiting_name;
        this.sponsor_id = sponsor_id;
        this.sponsor_name = sponsor_name;
        this.member_id = member_id;
        this.full_name = full_name;
        this.id_card = id_card;
        this.gender = gender;
        this.pob = pob;
        this.dob = dob;
        this.religion = religion;
        this.email = email;
        this.npwp = npwp;
        this.pekerjaan = pekerjaan;
        this.status_pernikahan = status_pernikahan;
        this.suami = suami;
        this.jumlah_anak = jumlah_anak;
        this.phone_number = phone_number;
        this.fax = fax;
//        this.whatsapp_number = whatsapp_number;
        this.mobile_number = mobile_number;
        this.address = address;
        this.province = province;
        this.city = city;
        this.city_id = city_id;
        this.postal_code = postal_code;
//        this.activation_code = activation_code;
        this.nama_pewaris = nama_pewaris;
        this.relationship = relationship;
        this.bank_id = bank_id;
        this.bank_name = bank_name;
        this.branch = branch;
        this.account_holder_name = account_holder_name;
        this.account_number = account_number;
    }

    protected ProfileData(Parcel in) {
        tanggal_daftar = in.readString();
        tanggal_expired = in.readString();
        stockist_id = in.readString();
        stockist_name = in.readString();
        recruiting_id = in.readString();
        recruiting_name = in.readString();
        sponsor_id = in.readString();
        sponsor_name = in.readString();
        member_id = in.readString();
        full_name = in.readString();
        id_card = in.readString();
        gender = in.readString();
        pob = in.readString();
        dob = in.readString();
        religion = in.readString();
        email = in.readString();
        npwp = in.readString();
        pekerjaan = in.readString();
        status_pernikahan = in.readString();
        suami = in.readString();
        jumlah_anak = in.readString();
        phone_number = in.readString();
        fax = in.readString();
        mobile_number = in.readString();
        address = in.readString();
        province = in.readString();
        city = in.readString();
        city_id = in.readString();
        postal_code = in.readString();
        nama_pewaris = in.readString();
        relationship = in.readString();
        bank_id = in.readString();
        bank_name = in.readString();
        branch = in.readString();
        account_holder_name = in.readString();
        account_number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tanggal_daftar);
        dest.writeString(tanggal_expired);
        dest.writeString(stockist_id);
        dest.writeString(stockist_name);
        dest.writeString(recruiting_id);
        dest.writeString(recruiting_name);
        dest.writeString(sponsor_id);
        dest.writeString(sponsor_name);
        dest.writeString(member_id);
        dest.writeString(full_name);
        dest.writeString(id_card);
        dest.writeString(gender);
        dest.writeString(pob);
        dest.writeString(dob);
        dest.writeString(religion);
        dest.writeString(email);
        dest.writeString(npwp);
        dest.writeString(pekerjaan);
        dest.writeString(status_pernikahan);
        dest.writeString(suami);
        dest.writeString(jumlah_anak);
        dest.writeString(phone_number);
        dest.writeString(fax);
        dest.writeString(mobile_number);
        dest.writeString(address);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(city_id);
        dest.writeString(postal_code);
        dest.writeString(nama_pewaris);
        dest.writeString(relationship);
        dest.writeString(bank_id);
        dest.writeString(bank_name);
        dest.writeString(branch);
        dest.writeString(account_holder_name);
        dest.writeString(account_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };
}
