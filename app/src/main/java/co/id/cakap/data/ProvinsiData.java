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
public class ProvinsiData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    protected ProvinsiData(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Generated(hash = 535348036)
    public ProvinsiData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 978800133)
    public ProvinsiData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<ProvinsiData> CREATOR = new Creator<ProvinsiData>() {
        @Override
        public ProvinsiData createFromParcel(Parcel in) {
            return new ProvinsiData(in);
        }

        @Override
        public ProvinsiData[] newArray(int size) {
            return new ProvinsiData[size];
        }
    };
}
