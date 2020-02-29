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
public class RestockReceiveStockData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String item_id;

    @SerializedName("inv")
    @Expose
    private String transaction_id;

    @SerializedName("ftotalharga")
    @Expose
    private String total_amount;

    @SerializedName("ftotalpv")
    @Expose
    private String total_pv;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_stc")
    @Expose
    private String no_stc;

    @SerializedName("remarkapp")
    @Expose
    private String remarkapp;

    @SerializedName("date")
    @Expose
    private String date;

    protected RestockReceiveStockData(Parcel in) {
        item_id = in.readString();
        transaction_id = in.readString();
        total_amount = in.readString();
        total_pv = in.readString();
        nama = in.readString();
        no_stc = in.readString();
        remarkapp = in.readString();
        date = in.readString();
    }

    @Generated(hash = 379505545)
    public RestockReceiveStockData(String item_id, String transaction_id, String total_amount, String total_pv,
            String nama, String no_stc, String remarkapp, String date) {
        this.item_id = item_id;
        this.transaction_id = transaction_id;
        this.total_amount = total_amount;
        this.total_pv = total_pv;
        this.nama = nama;
        this.no_stc = no_stc;
        this.remarkapp = remarkapp;
        this.date = date;
    }

    @Generated(hash = 824612002)
    public RestockReceiveStockData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(transaction_id);
        dest.writeString(total_amount);
        dest.writeString(total_pv);
        dest.writeString(nama);
        dest.writeString(no_stc);
        dest.writeString(remarkapp);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTotal_amount() {
        return this.total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_pv() {
        return this.total_pv;
    }

    public void setTotal_pv(String total_pv) {
        this.total_pv = total_pv;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_stc() {
        return this.no_stc;
    }

    public void setNo_stc(String no_stc) {
        this.no_stc = no_stc;
    }

    public String getRemarkapp() {
        return this.remarkapp;
    }

    public void setRemarkapp(String remarkapp) {
        this.remarkapp = remarkapp;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static final Creator<RestockReceiveStockData> CREATOR = new Creator<RestockReceiveStockData>() {
        @Override
        public RestockReceiveStockData createFromParcel(Parcel in) {
            return new RestockReceiveStockData(in);
        }

        @Override
        public RestockReceiveStockData[] newArray(int size) {
            return new RestockReceiveStockData[size];
        }
    };
}
