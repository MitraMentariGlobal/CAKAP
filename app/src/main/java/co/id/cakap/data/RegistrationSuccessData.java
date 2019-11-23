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
public class RegistrationSuccessData implements Parcelable {
    @SerializedName("recruiting_id")
    @Expose
    private String recruiting_id;

    @SerializedName("recruiting_name")
    @Expose
    private String recruiting_name;

    @SerializedName("sponsor_id")
    @Expose
    private String sponsor_id;

    @SerializedName("sponsor_name")
    @Expose
    private String sponsor_name;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("id_card")
    @Expose
    private String id_card;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("pob")
    @Expose
    private String pob;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("religion")
    @Expose
    private String religion;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("mobile_number")
    @Expose
    private String mobile_number;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("province")
    @Expose
    private String province;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("postal_code")
    @Expose
    private String postal_code;

    @SerializedName("activation_code")
    @Expose
    private String activation_code;

    @SerializedName("nama_pewaris")
    @Expose
    private String nama_pewaris;

    @SerializedName("relationship")
    @Expose
    private String relationship;

    @SerializedName("bank_name")
    @Expose
    private String bank_name;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("account_holder_name")
    @Expose
    private String account_holder_name;

    @SerializedName("account_number")
    @Expose
    private String account_number;

    @Keep
    public RegistrationSuccessData(
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
            String phone_number,
            String mobile_number,
            String address,
            String province,
            String city,
            String postal_code,
            String activation_code,
            String nama_pewaris,
            String relationship,
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
        this.phone_number = phone_number;
        this.mobile_number = mobile_number;
        this.address = address;
        this.province = province;
        this.city = city;
        this.postal_code = postal_code;
        this.activation_code = activation_code;
        this.nama_pewaris = nama_pewaris;
        this.relationship = relationship;
        this.bank_name = bank_name;
        this.branch = branch;
        this.account_holder_name = account_holder_name;
        this.account_number = account_number;
    }

    protected RegistrationSuccessData(Parcel in) {
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
        phone_number = in.readString();
        mobile_number = in.readString();
        address = in.readString();
        province = in.readString();
        city = in.readString();
        postal_code = in.readString();
        activation_code = in.readString();
        nama_pewaris = in.readString();
        relationship = in.readString();
        bank_name = in.readString();
        branch = in.readString();
        account_holder_name = in.readString();
        account_number = in.readString();
    }

    @Generated(hash = 1544243016)
    public RegistrationSuccessData() {
    }

    public static final Creator<RegistrationSuccessData> CREATOR = new Creator<RegistrationSuccessData>() {
        @Override
        public RegistrationSuccessData createFromParcel(Parcel in) {
            return new RegistrationSuccessData(in);
        }

        @Override
        public RegistrationSuccessData[] newArray(int size) {
            return new RegistrationSuccessData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
        dest.writeString(phone_number);
        dest.writeString(mobile_number);
        dest.writeString(address);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(postal_code);
        dest.writeString(activation_code);
        dest.writeString(nama_pewaris);
        dest.writeString(relationship);
        dest.writeString(bank_name);
        dest.writeString(branch);
        dest.writeString(account_holder_name);
        dest.writeString(account_number);
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

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getActivation_code() {
        return this.activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
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
}
