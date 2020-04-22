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
public class StockUpdateData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_code;

    @SerializedName("name")
    @Expose
    private String product_name;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("price")
    @Expose
    private String amount;

    @SerializedName("jmlpv")
    @Expose
    private String jmlpv;

    @Keep
    public StockUpdateData(
            String item_code,
            String product_name,
            String qty,
            String amount,
            String jmlpv
    ) {
        this.item_code = item_code;
        this.product_name = product_name;
        this.qty = qty;
        this.amount = amount;
        this.jmlpv = jmlpv;
    }

    protected StockUpdateData(Parcel in) {
        item_code = in.readString();
        product_name = in.readString();
        qty = in.readString();
        amount = in.readString();
        jmlpv = in.readString();
    }

    @Generated(hash = 970784101)
    public StockUpdateData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
        dest.writeString(product_name);
        dest.writeString(qty);
        dest.writeString(amount);
        dest.writeString(jmlpv);
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

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getJmlpv() {
        return this.jmlpv;
    }

    public void setJmlpv(String jmlpv) {
        this.jmlpv = jmlpv;
    }

    public static final Creator<StockUpdateData> CREATOR = new Creator<StockUpdateData>() {
        @Override
        public StockUpdateData createFromParcel(Parcel in) {
            return new StockUpdateData(in);
        }

        @Override
        public StockUpdateData[] newArray(int size) {
            return new StockUpdateData[size];
        }
    };
}
