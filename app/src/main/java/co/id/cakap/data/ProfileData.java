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

//@Data
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

    @SerializedName("tgllahir")
    @Expose
    private String dob;

    @SerializedName("ftgllahir")
    @Expose
    private String fdob;

    @SerializedName("religion")
    @Expose
    private String religion;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("npwp")
    @Expose
    private String npwp;

    @SerializedName("nonpwp")
    @Expose
    private String nonpwp;

    @SerializedName("office")
    @Expose
    private String pekerjaan;

    @SerializedName("menikah")
    @Expose
    private String status_pernikahan;

    @SerializedName("mnkh")
    @Expose
    private String status_mnkh;

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
            String fdob,
            String religion,
            String email,
            String npwp,
            String nonpwp,
            String pekerjaan,
            String status_pernikahan,
            String status_mnkh,
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
        this.fdob = fdob;
        this.religion = religion;
        this.email = email;
        this.npwp = npwp;
        this.nonpwp = nonpwp;
        this.pekerjaan = pekerjaan;
        this.status_pernikahan = status_pernikahan;
        this.status_mnkh = status_mnkh;
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
        fdob = in.readString();
        religion = in.readString();
        email = in.readString();
        npwp = in.readString();
        nonpwp = in.readString();
        pekerjaan = in.readString();
        status_pernikahan = in.readString();
        status_mnkh = in.readString();
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

    @Generated(hash = 1246655349)
    public ProfileData() {
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
        dest.writeString(fdob);
        dest.writeString(religion);
        dest.writeString(email);
        dest.writeString(npwp);
        dest.writeString(nonpwp);
        dest.writeString(pekerjaan);
        dest.writeString(status_pernikahan);
        dest.writeString(status_mnkh);
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

    public String getTanggal_daftar() {
        return this.tanggal_daftar;
    }

    public void setTanggal_daftar(String tanggal_daftar) {
        this.tanggal_daftar = tanggal_daftar;
    }

    public String getTanggal_expired() {
        return this.tanggal_expired;
    }

    public void setTanggal_expired(String tanggal_expired) {
        this.tanggal_expired = tanggal_expired;
    }

    public String getStockist_id() {
        return this.stockist_id;
    }

    public void setStockist_id(String stockist_id) {
        this.stockist_id = stockist_id;
    }

    public String getStockist_name() {
        return this.stockist_name;
    }

    public void setStockist_name(String stockist_name) {
        this.stockist_name = stockist_name;
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

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId_card() {
        return this.id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPob() {
        return this.pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFdob() {
        return this.fdob;
    }

    public void setFdob(String fdob) {
        this.fdob = fdob;
    }

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNpwp() {
        return this.npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getNonpwp() {
        return this.nonpwp;
    }

    public void setNonpwp(String nonpwp) {
        this.nonpwp = nonpwp;
    }

    public String getPekerjaan() {
        return this.pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getStatus_pernikahan() {
        return this.status_pernikahan;
    }

    public void setStatus_pernikahan(String status_pernikahan) {
        this.status_pernikahan = status_pernikahan;
    }

    public String getStatus_mnkh() {
        return this.status_mnkh;
    }

    public void setStatus_mnkh(String status_mnkh) {
        this.status_mnkh = status_mnkh;
    }

    public String getSuami() {
        return this.suami;
    }

    public void setSuami(String suami) {
        this.suami = suami;
    }

    public String getJumlah_anak() {
        return this.jumlah_anak;
    }

    public void setJumlah_anak(String jumlah_anak) {
        this.jumlah_anak = jumlah_anak;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile_number() {
        return this.mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return this.city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getNama_pewaris() {
        return this.nama_pewaris;
    }

    public void setNama_pewaris(String nama_pewaris) {
        this.nama_pewaris = nama_pewaris;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBank_id() {
        return this.bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount_holder_name() {
        return this.account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getAccount_number() {
        return this.account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
