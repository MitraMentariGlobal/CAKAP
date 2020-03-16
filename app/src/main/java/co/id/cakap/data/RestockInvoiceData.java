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
public class RestockInvoiceData implements Parcelable {
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

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("no_stc")
    @Expose
    private String no_stc;

    @SerializedName("remarkapp")
    @Expose
    private String remarkapp;

    @SerializedName("status")
    @Expose
    private String status;

    protected RestockInvoiceData(Parcel in) {
        item_id = in.readString();
        transaction_id = in.readString();
        total_amount = in.readString();
        total_pv = in.readString();
        date = in.readString();
        nama = in.readString();
        no_stc = in.readString();
        remarkapp = in.readString();
        status = in.readString();
    }

    @Generated(hash = 400022397)
    public RestockInvoiceData(String item_id, String transaction_id, String total_amount,
            String total_pv, String date, String nama, String no_stc, String remarkapp, String status) {
        this.item_id = item_id;
        this.transaction_id = transaction_id;
        this.total_amount = total_amount;
        this.total_pv = total_pv;
        this.date = date;
        this.nama = nama;
        this.no_stc = no_stc;
        this.remarkapp = remarkapp;
        this.status = status;
    }

    @Generated(hash = 1003735311)
    public RestockInvoiceData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(transaction_id);
        dest.writeString(total_amount);
        dest.writeString(total_pv);
        dest.writeString(date);
        dest.writeString(nama);
        dest.writeString(no_stc);
        dest.writeString(remarkapp);
        dest.writeString(status);
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final Creator<RestockInvoiceData> CREATOR = new Creator<RestockInvoiceData>() {
        @Override
        public RestockInvoiceData createFromParcel(Parcel in) {
            return new RestockInvoiceData(in);
        }

        @Override
        public RestockInvoiceData[] newArray(int size) {
            return new RestockInvoiceData[size];
        }
    };
}
