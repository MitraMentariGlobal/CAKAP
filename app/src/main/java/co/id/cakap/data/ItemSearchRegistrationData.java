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
public class ItemSearchRegistrationData implements Parcelable {

    @SerializedName("member_id")
    @Expose
    private String member_id;

    @SerializedName("name")
    @Expose
    private String name;

    @Keep
    public ItemSearchRegistrationData(String member_id, String name) {
        this.member_id = member_id;
        this.name = name;
    }

    protected ItemSearchRegistrationData(Parcel in) {
        member_id = in.readString();
        name = in.readString();
    }

    @Generated(hash = 1476646378)
    public ItemSearchRegistrationData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(member_id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<ItemSearchRegistrationData> CREATOR = new Creator<ItemSearchRegistrationData>() {
        @Override
        public ItemSearchRegistrationData createFromParcel(Parcel in) {
            return new ItemSearchRegistrationData(in);
        }

        @Override
        public ItemSearchRegistrationData[] newArray(int size) {
            return new ItemSearchRegistrationData[size];
        }
    };
}
