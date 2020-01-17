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
public class DetailTransactionData implements Parcelable {
    @SerializedName("item_id")
    @Expose
    private String item_code;

    @SerializedName("name")
    @Expose
    private String item_name;

    @SerializedName("fharga")
    @Expose
    private String price;

    @SerializedName("fpv")
    @Expose
    private String pv;

    @SerializedName("fqty")
    @Expose
    private String qty;

    @SerializedName("fsubtotal")
    @Expose
    private String sub_total;

    @SerializedName("fsubtotals")
    @Expose
    private String sub_totals;

    @SerializedName("fsubtotalpv")
    @Expose
    private String sub_total_pv;

//    @Keep
//    public DetailTransactionData(String item_code, String item_name, String price, String pv, String qty, String sub_total, String sub_total_pv) {
//        this.item_code = item_code;
//        this.item_name = item_name;
//        this.price = price;
//        this.pv = pv;
//        this.qty = qty;
//        this.sub_total = sub_total;
//        this.sub_total_pv = sub_total_pv;
//    }

    protected DetailTransactionData(Parcel in) {
        item_code = in.readString();
        item_name = in.readString();
        price = in.readString();
        pv = in.readString();
        qty = in.readString();
        sub_total = in.readString();
        sub_totals = in.readString();
        sub_total_pv = in.readString();
    }

    @Generated(hash = 244861625)
    public DetailTransactionData(String item_code, String item_name, String price, String pv, String qty, String sub_total, String sub_totals,
            String sub_total_pv) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.price = price;
        this.pv = pv;
        this.qty = qty;
        this.sub_total = sub_total;
        this.sub_totals = sub_totals;
        this.sub_total_pv = sub_total_pv;
    }

    @Generated(hash = 243614715)
    public DetailTransactionData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
        dest.writeString(item_name);
        dest.writeString(price);
        dest.writeString(pv);
        dest.writeString(qty);
        dest.writeString(sub_total);
        dest.writeString(sub_totals);
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

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSub_total() {
        return this.sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getSub_totals() {
        return this.sub_totals;
    }

    public void setSub_totals(String sub_totals) {
        this.sub_totals = sub_totals;
    }

    public String getSub_total_pv() {
        return this.sub_total_pv;
    }

    public void setSub_total_pv(String sub_total_pv) {
        this.sub_total_pv = sub_total_pv;
    }

    public static final Creator<DetailTransactionData> CREATOR = new Creator<DetailTransactionData>() {
        @Override
        public DetailTransactionData createFromParcel(Parcel in) {
            return new DetailTransactionData(in);
        }

        @Override
        public DetailTransactionData[] newArray(int size) {
            return new DetailTransactionData[size];
        }
    };
}
