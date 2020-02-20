package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class SubmitCashbillParentData implements Parcelable{
    @SerializedName("detail")
    @Expose
    private List<CashbillSuccessData> detail;

    protected SubmitCashbillParentData(Parcel in) {
        detail = in.createTypedArrayList(CashbillSuccessData.CREATOR);
    }

    public SubmitCashbillParentData() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubmitCashbillParentData> CREATOR = new Creator<SubmitCashbillParentData>() {
        @Override
        public SubmitCashbillParentData createFromParcel(Parcel in) {
            return new SubmitCashbillParentData(in);
        }

        @Override
        public SubmitCashbillParentData[] newArray(int size) {
            return new SubmitCashbillParentData[size];
        }
    };
}
