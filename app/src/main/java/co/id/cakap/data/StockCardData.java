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
public class StockCardData implements Parcelable {
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("stok_in")
    @Expose
    private String stok_in;

    @SerializedName("stok_out")
    @Expose
    private String stok_out;

    @SerializedName("saldo")
    @Expose
    private String saldo;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @Keep
    public StockCardData(
            String date,
            String product_name,
            String description,
            String stok_in,
            String stok_out,
            String saldo,
            String user_id
    ) {
        this.date = date;
        this.product_name = product_name;
        this.description = description;
        this.stok_in = stok_in;
        this.stok_out = stok_out;
        this.saldo = saldo;
        this.user_id = user_id;
    }

    protected StockCardData(Parcel in) {
        date = in.readString();
        product_name = in.readString();
        description = in.readString();
        stok_in = in.readString();
        stok_out = in.readString();
        saldo = in.readString();
        user_id = in.readString();
    }

    @Generated(hash = 1027024101)
    public StockCardData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(product_name);
        dest.writeString(description);
        dest.writeString(stok_in);
        dest.writeString(stok_out);
        dest.writeString(saldo);
        dest.writeString(user_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStok_in() {
        return this.stok_in;
    }

    public void setStok_in(String stok_in) {
        this.stok_in = stok_in;
    }

    public String getStok_out() {
        return this.stok_out;
    }

    public void setStok_out(String stok_out) {
        this.stok_out = stok_out;
    }

    public String getSaldo() {
        return this.saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static final Creator<StockCardData> CREATOR = new Creator<StockCardData>() {
        @Override
        public StockCardData createFromParcel(Parcel in) {
            return new StockCardData(in);
        }

        @Override
        public StockCardData[] newArray(int size) {
            return new StockCardData[size];
        }
    };
}
