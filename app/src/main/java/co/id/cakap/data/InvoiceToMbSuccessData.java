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
public class InvoiceToMbSuccessData implements Parcelable {
    @SerializedName("item_code")
    @Expose
    private String item_code;

    @SerializedName("item_name")
    @Expose
    private String item_name;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("pv")
    @Expose
    private String pv;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("sub_total")
    @Expose
    private String sub_total;

    @SerializedName("sub_total_pv")
    @Expose
    private String sub_total_pv;

    @Keep
    public InvoiceToMbSuccessData(String item_code, String item_name, String price, String pv, String qty, String sub_total, String sub_total_pv) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.price = price;
        this.pv = pv;
        this.qty = qty;
        this.sub_total = sub_total;
        this.sub_total_pv = sub_total_pv;
    }

    protected InvoiceToMbSuccessData(Parcel in) {
        item_code = in.readString();
        item_name = in.readString();
        price = in.readString();
        pv = in.readString();
        qty = in.readString();
        sub_total = in.readString();
        sub_total_pv = in.readString();
    }

    @Generated(hash = 377057178)
    public InvoiceToMbSuccessData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
        dest.writeString(item_name);
        dest.writeString(price);
        dest.writeString(pv);
        dest.writeString(qty);
        dest.writeString(sub_total);
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

    public String getSub_total_pv() {
        return this.sub_total_pv;
    }

    public void setSub_total_pv(String sub_total_pv) {
        this.sub_total_pv = sub_total_pv;
    }

    public static final Creator<InvoiceToMbSuccessData> CREATOR = new Creator<InvoiceToMbSuccessData>() {
        @Override
        public InvoiceToMbSuccessData createFromParcel(Parcel in) {
            return new InvoiceToMbSuccessData(in);
        }

        @Override
        public InvoiceToMbSuccessData[] newArray(int size) {
            return new InvoiceToMbSuccessData[size];
        }
    };
}
