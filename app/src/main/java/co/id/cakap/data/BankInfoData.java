package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
@Entity
public class BankInfoData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("accountname")
    @Expose
    private String accountname;

    @SerializedName("accountno")
    @Expose
    private String accountno;

    protected BankInfoData(Parcel in) {
        id = in.readString();
        accountname = in.readString();
        accountno = in.readString();
    }

    @Generated(hash = 1282278256)
    public BankInfoData(String id, String accountname, String accountno) {
        this.id = id;
        this.accountname = accountname;
        this.accountno = accountno;
    }

    @Generated(hash = 1175287340)
    public BankInfoData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(accountname);
        dest.writeString(accountno);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountname() {
        return this.accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountno() {
        return this.accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public static final Creator<BankInfoData> CREATOR = new Creator<BankInfoData>() {
        @Override
        public BankInfoData createFromParcel(Parcel in) {
            return new BankInfoData(in);
        }

        @Override
        public BankInfoData[] newArray(int size) {
            return new BankInfoData[size];
        }
    };
}
