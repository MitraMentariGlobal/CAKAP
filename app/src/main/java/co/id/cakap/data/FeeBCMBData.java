package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;

import lombok.Data;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class FeeBCMBData implements Parcelable {
    @SerializedName("total_omset_bc_dan_mb_setelah_ppn")
    @Expose
    private String total_omset_bc_dan_mb_setelah_ppn;

    @SerializedName("total_omzet_setelah_ppn")
    @Expose
    private String total_omset_setelah_ppn;

    @SerializedName("total_omzet_product_setelah_ppn")
    @Expose
    private String total_omset_product_setelah_ppn;

    @SerializedName("total_starter_kit_sp01")
    @Expose
    private String total_starter_kit_sp01;

    @SerializedName("total_starter_kit_sp03")
    @Expose
    private String total_starter_kit_sp03;

    @SerializedName("total_starter_kit_sp04")
    @Expose
    private String total_starter_kit_sp04;

    @SerializedName("bonus_total_omset_produk_")
    @Expose
    private String bonus_total_omset_product;

//    @SerializedName("tambahan_bonus_total_omset")
//    @Expose
//    private String tambahan_bonus_total_omset;

    @SerializedName("bonus_starer_kit_sp01")
    @Expose
    private String bonus_stater_kit_basic;

    @SerializedName("bonus_starter_kit_sp03")
    @Expose
    private String bonus_paket_kombinasi_lengkap;

    @SerializedName("bonus_reff_mb")
    @Expose
    private String bonus_reff_mb;

    @SerializedName("bonus_stater_kit_sp04")
    @Expose
    private String bonus_kit_v_bless;

    @SerializedName("total_fee")
    @Expose
    private double total_fee;

    @SerializedName("pajak")
    @Expose
    private double pajak;

    @SerializedName("netto_fee")
    @Expose
    private double netto_fee;

    protected FeeBCMBData(Parcel in) {
        total_omset_bc_dan_mb_setelah_ppn = in.readString();
        total_omset_setelah_ppn = in.readString();
        total_omset_product_setelah_ppn = in.readString();
        total_starter_kit_sp01 = in.readString();
        total_starter_kit_sp03 = in.readString();
        total_starter_kit_sp04 = in.readString();
        bonus_total_omset_product = in.readString();
        bonus_stater_kit_basic = in.readString();
        bonus_paket_kombinasi_lengkap = in.readString();
        bonus_reff_mb = in.readString();
        bonus_kit_v_bless = in.readString();
        total_fee = in.readDouble();
        pajak = in.readDouble();
        netto_fee = in.readDouble();
    }

    @Generated(hash = 1419089312)
    public FeeBCMBData(String total_omset_bc_dan_mb_setelah_ppn,
            String total_omset_setelah_ppn, String total_omset_product_setelah_ppn,
            String total_starter_kit_sp01, String total_starter_kit_sp03,
            String total_starter_kit_sp04, String bonus_total_omset_product,
            String bonus_stater_kit_basic, String bonus_paket_kombinasi_lengkap,
            String bonus_reff_mb, String bonus_kit_v_bless, double total_fee,
            double pajak, double netto_fee) {
        this.total_omset_bc_dan_mb_setelah_ppn = total_omset_bc_dan_mb_setelah_ppn;
        this.total_omset_setelah_ppn = total_omset_setelah_ppn;
        this.total_omset_product_setelah_ppn = total_omset_product_setelah_ppn;
        this.total_starter_kit_sp01 = total_starter_kit_sp01;
        this.total_starter_kit_sp03 = total_starter_kit_sp03;
        this.total_starter_kit_sp04 = total_starter_kit_sp04;
        this.bonus_total_omset_product = bonus_total_omset_product;
        this.bonus_stater_kit_basic = bonus_stater_kit_basic;
        this.bonus_paket_kombinasi_lengkap = bonus_paket_kombinasi_lengkap;
        this.bonus_reff_mb = bonus_reff_mb;
        this.bonus_kit_v_bless = bonus_kit_v_bless;
        this.total_fee = total_fee;
        this.pajak = pajak;
        this.netto_fee = netto_fee;
    }

    @Generated(hash = 1066607655)
    public FeeBCMBData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(total_omset_bc_dan_mb_setelah_ppn);
        dest.writeString(total_omset_setelah_ppn);
        dest.writeString(total_omset_product_setelah_ppn);
        dest.writeString(total_starter_kit_sp01);
        dest.writeString(total_starter_kit_sp03);
        dest.writeString(total_starter_kit_sp04);
        dest.writeString(bonus_total_omset_product);
        dest.writeString(bonus_stater_kit_basic);
        dest.writeString(bonus_paket_kombinasi_lengkap);
        dest.writeString(bonus_reff_mb);
        dest.writeString(bonus_kit_v_bless);
        dest.writeDouble(total_fee);
        dest.writeDouble(pajak);
        dest.writeDouble(netto_fee);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTotal_omset_bc_dan_mb_setelah_ppn() {
        return this.total_omset_bc_dan_mb_setelah_ppn;
    }

    public void setTotal_omset_bc_dan_mb_setelah_ppn(
            String total_omset_bc_dan_mb_setelah_ppn) {
        this.total_omset_bc_dan_mb_setelah_ppn = total_omset_bc_dan_mb_setelah_ppn;
    }

    public String getTotal_omset_setelah_ppn() {
        return this.total_omset_setelah_ppn;
    }

    public void setTotal_omset_setelah_ppn(String total_omset_setelah_ppn) {
        this.total_omset_setelah_ppn = total_omset_setelah_ppn;
    }

    public String getTotal_omset_product_setelah_ppn() {
        return this.total_omset_product_setelah_ppn;
    }

    public void setTotal_omset_product_setelah_ppn(
            String total_omset_product_setelah_ppn) {
        this.total_omset_product_setelah_ppn = total_omset_product_setelah_ppn;
    }

    public String getTotal_starter_kit_sp01() {
        return this.total_starter_kit_sp01;
    }

    public void setTotal_starter_kit_sp01(String total_starter_kit_sp01) {
        this.total_starter_kit_sp01 = total_starter_kit_sp01;
    }

    public String getTotal_starter_kit_sp03() {
        return this.total_starter_kit_sp03;
    }

    public void setTotal_starter_kit_sp03(String total_starter_kit_sp03) {
        this.total_starter_kit_sp03 = total_starter_kit_sp03;
    }

    public String getTotal_starter_kit_sp04() {
        return this.total_starter_kit_sp04;
    }

    public void setTotal_starter_kit_sp04(String total_starter_kit_sp04) {
        this.total_starter_kit_sp04 = total_starter_kit_sp04;
    }

    public String getBonus_total_omset_product() {
        return this.bonus_total_omset_product;
    }

    public void setBonus_total_omset_product(String bonus_total_omset_product) {
        this.bonus_total_omset_product = bonus_total_omset_product;
    }

    public String getBonus_stater_kit_basic() {
        return this.bonus_stater_kit_basic;
    }

    public void setBonus_stater_kit_basic(String bonus_stater_kit_basic) {
        this.bonus_stater_kit_basic = bonus_stater_kit_basic;
    }

    public String getBonus_paket_kombinasi_lengkap() {
        return this.bonus_paket_kombinasi_lengkap;
    }

    public void setBonus_paket_kombinasi_lengkap(
            String bonus_paket_kombinasi_lengkap) {
        this.bonus_paket_kombinasi_lengkap = bonus_paket_kombinasi_lengkap;
    }

    public String getBonus_reff_mb() {
        return this.bonus_reff_mb;
    }

    public void setBonus_reff_mb(String bonus_reff_mb) {
        this.bonus_reff_mb = bonus_reff_mb;
    }

    public String getBonus_kit_v_bless() {
        return this.bonus_kit_v_bless;
    }

    public void setBonus_kit_v_bless(String bonus_kit_v_bless) {
        this.bonus_kit_v_bless = bonus_kit_v_bless;
    }

    public double getTotal_fee() {
        return this.total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public double getPajak() {
        return this.pajak;
    }

    public void setPajak(double pajak) {
        this.pajak = pajak;
    }

    public double getNetto_fee() {
        return this.netto_fee;
    }

    public void setNetto_fee(double netto_fee) {
        this.netto_fee = netto_fee;
    }

    public static final Creator<FeeBCMBData> CREATOR = new Creator<FeeBCMBData>() {
        @Override
        public FeeBCMBData createFromParcel(Parcel in) {
            return new FeeBCMBData(in);
        }

        @Override
        public FeeBCMBData[] newArray(int size) {
            return new FeeBCMBData[size];
        }
    };
}
