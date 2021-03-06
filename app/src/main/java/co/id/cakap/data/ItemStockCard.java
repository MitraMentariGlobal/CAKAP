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
public class ItemStockCard implements Parcelable {
    @SerializedName("item_id")
    @Expose
    private String item_code;

    @SerializedName("name")
    @Expose
    private String item_name;

    @SerializedName("harga")
    @Expose
    private String amount;

    @Keep
    public ItemStockCard(
            String item_code,
            String item_name,
            String amount
    ) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.amount = amount;
    }

    protected ItemStockCard(Parcel in) {
        item_code = in.readString();
        item_name = in.readString();
        amount = in.readString();
    }

    @Generated(hash = 1039474827)
    public ItemStockCard() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_code);
        dest.writeString(item_name);
        dest.writeString(amount);
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

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public static final Creator<ItemStockCard> CREATOR = new Creator<ItemStockCard>() {
        @Override
        public ItemStockCard createFromParcel(Parcel in) {
            return new ItemStockCard(in);
        }

        @Override
        public ItemStockCard[] newArray(int size) {
            return new ItemStockCard[size];
        }
    };
}
