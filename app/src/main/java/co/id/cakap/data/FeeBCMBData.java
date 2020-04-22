package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

import java.util.List;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
//@Entity
public class FeeBCMBData implements Parcelable {
    @SerializedName("txt_omset")
    @Expose
    private List<InfoData> txt_omset;
    @SerializedName("txt_bonus")
    @Expose
    private List<InfoData> txt_bonus;
    @SerializedName("txt_bonus_footer")
    @Expose
    private List<InfoData> txt_bonus_footer;

//    @SerializedName("total_omset_bc_dan_mb_setelah_ppn")
//    @Expose
//    private String total_omset_bc_dan_mb_setelah_ppn;
//
//    @SerializedName("total_omzet_setelah_ppn")
//    @Expose
//    private String total_omset_setelah_ppn;
//
//    @SerializedName("total_omzet_product_setelah_ppn")
//    @Expose
//    private String total_omset_product_setelah_ppn;
//
//    @SerializedName("total_starter_kit_sp01")
//    @Expose
//    private String total_starter_kit_sp01;
//
//    @SerializedName("total_starter_kit_sp03")
//    @Expose
//    private String total_starter_kit_sp03;
//
//    @SerializedName("total_starter_kit_sp04")
//    @Expose
//    private String total_starter_kit_sp04;
//
//    @SerializedName("bonus_total_omset_produk_")
//    @Expose
//    private String bonus_total_omset_product;
//
////    @SerializedName("tambahan_bonus_total_omset")
////    @Expose
////    private String tambahan_bonus_total_omset;
//
//    @SerializedName("bonus_starter_kit_basic")
//    @Expose
//    private String bonus_stater_kit_basic;
//
//    @SerializedName("bonus_paket_kombinasi_lengkap")
//    @Expose
//    private String bonus_paket_kombinasi_lengkap;
//
//    @SerializedName("bonus_reff_mb")
//    @Expose
//    private String bonus_reff_mb;
//
//    @SerializedName("bonus_kit_v_bless")
//    @Expose
//    private String bonus_kit_v_bless;
//
//    @SerializedName("total_fee")
//    @Expose
//    private double total_fee;
//
//    @SerializedName("pajak")
//    @Expose
//    private double pajak;
//
//    @SerializedName("netto_fee")
//    @Expose
//    private double netto_fee;

    protected FeeBCMBData(Parcel in) {
        txt_omset = in.createTypedArrayList(InfoData.CREATOR);
        txt_bonus = in.createTypedArrayList(InfoData.CREATOR);
        txt_bonus_footer = in.createTypedArrayList(InfoData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(txt_omset);
        dest.writeTypedList(txt_bonus);
        dest.writeTypedList(txt_bonus_footer);
    }

    @Override
    public int describeContents() {
        return 0;
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
