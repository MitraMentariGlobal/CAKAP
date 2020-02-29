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
public class ItemShopData implements Parcelable {
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("item_id")
    @Expose
    private String item_code;

    @SerializedName("name")
    @Expose
    private String item_name;

    @SerializedName("harga")
    @Expose
    private String harga;

    @SerializedName("pv")
    @Expose
    private String pv;

    @SerializedName("bv")
    @Expose
    private String bv;

    @SerializedName("fharga")
    @Expose
    private String fharga;

    @SerializedName("fpv")
    @Expose
    private String fpv;

    @SerializedName("fbv")
    @Expose
    private String fbv;

    @SerializedName("fqty")
    @Expose
    private String fqty;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("ket")
    @Expose
    private String ket;

    @SerializedName("cart")
    @Expose
    private String cart;

    protected ItemShopData(Parcel in) {
        image = in.readString();
        id = in.readString();
        item_code = in.readString();
        item_name = in.readString();
        harga = in.readString();
        pv = in.readString();
        bv = in.readString();
        fharga = in.readString();
        fpv = in.readString();
        fbv = in.readString();
        fqty = in.readString();
        qty = in.readString();
        ket = in.readString();
        cart = in.readString();
    }

    @Generated(hash = 1275743)
    public ItemShopData(String image, String id, String item_code, String item_name,
            String harga, String pv, String bv, String fharga, String fpv, String fbv,
            String fqty, String qty, String ket, String cart) {
        this.image = image;
        this.id = id;
        this.item_code = item_code;
        this.item_name = item_name;
        this.harga = harga;
        this.pv = pv;
        this.bv = bv;
        this.fharga = fharga;
        this.fpv = fpv;
        this.fbv = fbv;
        this.fqty = fqty;
        this.qty = qty;
        this.ket = ket;
        this.cart = cart;
    }

    @Generated(hash = 1607569685)
    public ItemShopData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(id);
        dest.writeString(item_code);
        dest.writeString(item_name);
        dest.writeString(harga);
        dest.writeString(pv);
        dest.writeString(bv);
        dest.writeString(fharga);
        dest.writeString(fpv);
        dest.writeString(fbv);
        dest.writeString(fqty);
        dest.writeString(qty);
        dest.writeString(ket);
        dest.writeString(cart);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_code() {
        return this.item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return this.item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getHarga() {
        return this.harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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

    public String getFharga() {
        return this.fharga;
    }

    public void setFharga(String fharga) {
        this.fharga = fharga;
    }

    public String getFpv() {
        return this.fpv;
    }

    public void setFpv(String fpv) {
        this.fpv = fpv;
    }

    public String getFbv() {
        return this.fbv;
    }

    public void setFbv(String fbv) {
        this.fbv = fbv;
    }

    public String getFqty() {
        return this.fqty;
    }

    public void setFqty(String fqty) {
        this.fqty = fqty;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getKet() {
        return this.ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getCart() {
        return this.cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public static final Creator<ItemShopData> CREATOR = new Creator<ItemShopData>() {
        @Override
        public ItemShopData createFromParcel(Parcel in) {
            return new ItemShopData(in);
        }

        @Override
        public ItemShopData[] newArray(int size) {
            return new ItemShopData[size];
        }
    };
}
