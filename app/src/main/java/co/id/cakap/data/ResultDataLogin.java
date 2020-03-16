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
public class ResultDataLogin implements Parcelable {
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("session_token")
    @Expose
    private String session_token;

    @SerializedName("update_profile")
    @Expose
    private String update_profile;

    @SerializedName("running_text")
    @Expose
    private String running_text;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("posisi")
    @Expose
    private String posisi;

    @SerializedName("bonus")
    @Expose
    private String bonus;

    @SerializedName("pv_max")
    @Expose
    private String pv_max;

    @SerializedName("pv_tupo")
    @Expose
    private String pv_tupo;

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("group_id")
    @Expose
    private String group_id;

    @SerializedName("wilayah")
    @Expose
    private String wilayah;

    @SerializedName("leader_ids")
    @Expose
    private String leader_ids;

    @SerializedName("flag_login")
    @Expose
    private String flag_login;

    @SerializedName("ewallet")
    @Expose
    private String ewallet;

    @SerializedName("fee")
    @Expose
    private String fee;

    @SerializedName("omset")
    @Expose
    private String omset;

    @SerializedName("total_omset")
    @Expose
    private String total_omset;

    @SerializedName("bulan_bonus")
    @Expose
    private String bulan_bonus;

    protected ResultDataLogin(Parcel in) {
        url = in.readString();
        role = in.readString();
        session_token = in.readString();
        update_profile = in.readString();
        running_text = in.readString();
        gambar = in.readString();
        nama = in.readString();
        username = in.readString();
        member_id = in.readString();
        posisi = in.readString();
        bonus = in.readString();
        pv_max = in.readString();
        pv_tupo = in.readString();
        userid = in.readString();
        group_id = in.readString();
        wilayah = in.readString();
        leader_ids = in.readString();
        flag_login = in.readString();
        ewallet = in.readString();
        fee = in.readString();
        omset = in.readString();
        total_omset = in.readString();
        bulan_bonus = in.readString();
    }

    @Generated(hash = 269693891)
    public ResultDataLogin(String url, String role, String session_token,
            String update_profile, String running_text, String gambar, String nama,
            String username, String member_id, String posisi, String bonus, String pv_max,
            String pv_tupo, String userid, String group_id, String wilayah, String leader_ids,
            String flag_login, String ewallet, String fee, String omset, String total_omset,
            String bulan_bonus) {
        this.url = url;
        this.role = role;
        this.session_token = session_token;
        this.update_profile = update_profile;
        this.running_text = running_text;
        this.gambar = gambar;
        this.nama = nama;
        this.username = username;
        this.member_id = member_id;
        this.posisi = posisi;
        this.bonus = bonus;
        this.pv_max = pv_max;
        this.pv_tupo = pv_tupo;
        this.userid = userid;
        this.group_id = group_id;
        this.wilayah = wilayah;
        this.leader_ids = leader_ids;
        this.flag_login = flag_login;
        this.ewallet = ewallet;
        this.fee = fee;
        this.omset = omset;
        this.total_omset = total_omset;
        this.bulan_bonus = bulan_bonus;
    }

    @Generated(hash = 820616836)
    public ResultDataLogin() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(role);
        dest.writeString(session_token);
        dest.writeString(update_profile);
        dest.writeString(running_text);
        dest.writeString(gambar);
        dest.writeString(nama);
        dest.writeString(username);
        dest.writeString(member_id);
        dest.writeString(posisi);
        dest.writeString(bonus);
        dest.writeString(pv_max);
        dest.writeString(pv_tupo);
        dest.writeString(userid);
        dest.writeString(group_id);
        dest.writeString(wilayah);
        dest.writeString(leader_ids);
        dest.writeString(flag_login);
        dest.writeString(ewallet);
        dest.writeString(fee);
        dest.writeString(omset);
        dest.writeString(total_omset);
        dest.writeString(bulan_bonus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSession_token() {
        return this.session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public String getUpdate_profile() {
        return this.update_profile;
    }

    public void setUpdate_profile(String update_profile) {
        this.update_profile = update_profile;
    }

    public String getRunning_text() {
        return this.running_text;
    }

    public void setRunning_text(String running_text) {
        this.running_text = running_text;
    }

    public String getGambar() {
        return this.gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPosisi() {
        return this.posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getBonus() {
        return this.bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getPv_max() {
        return this.pv_max;
    }

    public void setPv_max(String pv_max) {
        this.pv_max = pv_max;
    }

    public String getPv_tupo() {
        return this.pv_tupo;
    }

    public void setPv_tupo(String pv_tupo) {
        this.pv_tupo = pv_tupo;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getWilayah() {
        return this.wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getLeader_ids() {
        return this.leader_ids;
    }

    public void setLeader_ids(String leader_ids) {
        this.leader_ids = leader_ids;
    }

    public String getFlag_login() {
        return this.flag_login;
    }

    public void setFlag_login(String flag_login) {
        this.flag_login = flag_login;
    }

    public String getEwallet() {
        return this.ewallet;
    }

    public void setEwallet(String ewallet) {
        this.ewallet = ewallet;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getOmset() {
        return this.omset;
    }

    public void setOmset(String omset) {
        this.omset = omset;
    }

    public String getTotal_omset() {
        return this.total_omset;
    }

    public void setTotal_omset(String total_omset) {
        this.total_omset = total_omset;
    }

    public String getBulan_bonus() {
        return this.bulan_bonus;
    }

    public void setBulan_bonus(String bulan_bonus) {
        this.bulan_bonus = bulan_bonus;
    }

    public static final Creator<ResultDataLogin> CREATOR = new Creator<ResultDataLogin>() {
        @Override
        public ResultDataLogin createFromParcel(Parcel in) {
            return new ResultDataLogin(in);
        }

        @Override
        public ResultDataLogin[] newArray(int size) {
            return new ResultDataLogin[size];
        }
    };
}
