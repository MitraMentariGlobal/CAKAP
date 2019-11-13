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
public class ItemShopCompanyData implements Parcelable {
    @SerializedName("item_code")
    @Expose
    private String item_code;

    @SerializedName("item_name")
    @Expose
    private String item_name;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("pv")
    @Expose
    private String pv;

    @SerializedName("sub_total_price")
    @Expose
    private String sub_total_price;

    @SerializedName("sub_total_pv")
    @Expose
    private String sub_total_pv;

    @Keep
    public ItemShopCompanyData(String item_code, String item_name, String qty, String price, String pv, String sub_total_price, String sub_total_pv) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.qty = qty;
        this.price = price;
        this.pv = pv;
        this.sub_total_price = sub_total_price;
        this.sub_total_pv = sub_total_pv;
    }

    protected ItemShopCompanyData(Parcel in) {
        item_code = in.readString();
        item_name = in.readString();
        qty = in.readString();
        price = in.readString();
        pv = in.readString();
        sub_total_price = in.readString();
        sub_total_pv = in.readString();
    }

    @Generated(hash = 1215865971)
    public ItemShopCompanyData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
        dest.writeString(item_name);
        dest.writeString(qty);
        dest.writeString(price);
        dest.writeString(pv);
        dest.writeString(sub_total_price);
        dest.writeString(sub_total_pv);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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

    public String getSub_total_price() {
        return this.sub_total_price;
    }

    public void setSub_total_price(String sub_total_price) {
        this.sub_total_price = sub_total_price;
    }

    public String getSub_total_pv() {
        return this.sub_total_pv;
    }

    public void setSub_total_pv(String sub_total_pv) {
        this.sub_total_pv = sub_total_pv;
    }

    public static final Creator<ItemShopCompanyData> CREATOR = new Creator<ItemShopCompanyData>() {
        @Override
        public ItemShopCompanyData createFromParcel(Parcel in) {
            return new ItemShopCompanyData(in);
        }

        @Override
        public ItemShopCompanyData[] newArray(int size) {
            return new ItemShopCompanyData[size];
        }
    };
}
