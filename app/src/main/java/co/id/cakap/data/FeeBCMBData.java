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
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class FeeBCMBData implements Parcelable {
    @SerializedName("total_omset_setelah_ppn")
    @Expose
    private String total_omset_setelah_ppn;

    @SerializedName("total_omset_product_setelah_ppn")
    @Expose
    private String total_omset_product_setelah_ppn;

    @SerializedName("total_stater_kit_regular")
    @Expose
    private String total_stater_kit_regular;

    @SerializedName("total_paket_register_kombinasi")
    @Expose
    private String total_paket_register_kombinasi;

    @SerializedName("bonus_total_omset_product")
    @Expose
    private String bonus_total_omset_product;

    @SerializedName("tambahan_bonus_total_omset")
    @Expose
    private String tambahan_bonus_total_omset;

    @SerializedName("bonus_stater_kit_basic")
    @Expose
    private String bonus_stater_kit_basic;

    @SerializedName("bonus_paket_kombinasi")
    @Expose
    private String bonus_paket_kombinasi;

    @SerializedName("bonus_reff_mb")
    @Expose
    private String bonus_reff_mb;

    @SerializedName("bonus_kit_v_bless")
    @Expose
    private String bonus_kit_v_bless;

    @SerializedName("total_fee")
    @Expose
    private String total_fee;

    @Keep
    public FeeBCMBData(
            String total_omset_setelah_ppn,
            String total_omset_product_setelah_ppn,
            String total_stater_kit_regular,
            String total_paket_register_kombinasi,
            String bonus_total_omset_product,
            String tambahan_bonus_total_omset,
            String bonus_stater_kit_basic,
            String bonus_paket_kombinasi,
            String bonus_reff_mb,
            String bonus_kit_v_bless,
            String total_fee
    ) {
        this.total_omset_setelah_ppn = total_omset_setelah_ppn;
        this.total_omset_product_setelah_ppn = total_omset_product_setelah_ppn;
        this.total_stater_kit_regular = total_stater_kit_regular;
        this.total_paket_register_kombinasi = total_paket_register_kombinasi;
        this.bonus_total_omset_product = bonus_total_omset_product;
        this.tambahan_bonus_total_omset = tambahan_bonus_total_omset;
        this.bonus_stater_kit_basic = bonus_stater_kit_basic;
        this.bonus_paket_kombinasi = bonus_paket_kombinasi;
        this.bonus_reff_mb = bonus_reff_mb;
        this.bonus_kit_v_bless = bonus_kit_v_bless;
        this.total_fee = total_fee;
    }

    protected FeeBCMBData(Parcel in) {
        total_omset_setelah_ppn = in.readString();
        total_omset_product_setelah_ppn = in.readString();
        total_stater_kit_regular = in.readString();
        total_paket_register_kombinasi = in.readString();
        bonus_total_omset_product = in.readString();
        tambahan_bonus_total_omset = in.readString();
        bonus_stater_kit_basic = in.readString();
        bonus_paket_kombinasi = in.readString();
        bonus_reff_mb = in.readString();
        bonus_kit_v_bless = in.readString();
        total_fee = in.readString();
    }

    @Generated(hash = 1066607655)
    public FeeBCMBData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(total_omset_setelah_ppn);
        dest.writeString(total_omset_product_setelah_ppn);
        dest.writeString(total_stater_kit_regular);
        dest.writeString(total_paket_register_kombinasi);
        dest.writeString(bonus_total_omset_product);
        dest.writeString(tambahan_bonus_total_omset);
        dest.writeString(bonus_stater_kit_basic);
        dest.writeString(bonus_paket_kombinasi);
        dest.writeString(bonus_reff_mb);
        dest.writeString(bonus_kit_v_bless);
        dest.writeString(total_fee);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getTotal_stater_kit_regular() {
        return this.total_stater_kit_regular;
    }

    public void setTotal_stater_kit_regular(String total_stater_kit_regular) {
        this.total_stater_kit_regular = total_stater_kit_regular;
    }

    public String getTotal_paket_register_kombinasi() {
        return this.total_paket_register_kombinasi;
    }

    public void setTotal_paket_register_kombinasi(
            String total_paket_register_kombinasi) {
        this.total_paket_register_kombinasi = total_paket_register_kombinasi;
    }

    public String getBonus_total_omset_product() {
        return this.bonus_total_omset_product;
    }

    public void setBonus_total_omset_product(String bonus_total_omset_product) {
        this.bonus_total_omset_product = bonus_total_omset_product;
    }

    public String getTambahan_bonus_total_omset() {
        return this.tambahan_bonus_total_omset;
    }

    public void setTambahan_bonus_total_omset(String tambahan_bonus_total_omset) {
        this.tambahan_bonus_total_omset = tambahan_bonus_total_omset;
    }

    public String getBonus_stater_kit_basic() {
        return this.bonus_stater_kit_basic;
    }

    public void setBonus_stater_kit_basic(String bonus_stater_kit_basic) {
        this.bonus_stater_kit_basic = bonus_stater_kit_basic;
    }

    public String getBonus_paket_kombinasi() {
        return this.bonus_paket_kombinasi;
    }

    public void setBonus_paket_kombinasi(String bonus_paket_kombinasi) {
        this.bonus_paket_kombinasi = bonus_paket_kombinasi;
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

    public String getTotal_fee() {
        return this.total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
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
