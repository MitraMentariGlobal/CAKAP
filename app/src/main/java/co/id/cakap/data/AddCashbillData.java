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
public class AddCashbillData {
    @SerializedName("titipan_id")
    @Expose
    private String titipan_id;

    @SerializedName("item_id")
    @Expose
    private String item_id;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("pv")
    @Expose
    private String pv;

    @SerializedName("bv")
    @Expose
    private String bv;

    @SerializedName("qty")
    @Expose
    private String qty;

    @Keep
    public AddCashbillData(String titipan_id, String item_id, String price, String pv, String bv, String qty) {
        this.titipan_id = titipan_id;
        this.item_id = item_id;
        this.price = price;
        this.pv = pv;
        this.bv = bv;
        this.qty = qty;
    }

    @Generated(hash = 1032777808)
    public AddCashbillData() {
    }

    public String getTitipan_id() {
        return this.titipan_id;
    }

    public void setTitipan_id(String titipan_id) {
        this.titipan_id = titipan_id;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPv() {
        return this.pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getBv() {
        return this.bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
