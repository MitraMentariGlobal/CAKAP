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
public class NetworkGenealogyData implements Parcelable {
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("nama")
    @Expose
    private String nama;

    protected NetworkGenealogyData(Parcel in) {
        link = in.readString();
        nama = in.readString();
    }

    @Generated(hash = 1966290554)
    public NetworkGenealogyData(String link, String nama) {
        this.link = link;
        this.nama = nama;
    }

    @Generated(hash = 538777502)
    public NetworkGenealogyData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(link);
        dest.writeString(nama);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public static final Creator<NetworkGenealogyData> CREATOR = new Creator<NetworkGenealogyData>() {
        @Override
        public NetworkGenealogyData createFromParcel(Parcel in) {
            return new NetworkGenealogyData(in);
        }

        @Override
        public NetworkGenealogyData[] newArray(int size) {
            return new NetworkGenealogyData[size];
        }
    };
}
