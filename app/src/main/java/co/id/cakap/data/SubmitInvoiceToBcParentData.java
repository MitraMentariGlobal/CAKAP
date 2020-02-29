package co.id.cakap.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class SubmitInvoiceToBcParentData implements Parcelable{
    @SerializedName("detail")
    @Expose
    private List<ReqInvoiceToBcSuccessData> detail;

    protected SubmitInvoiceToBcParentData(Parcel in) {
        detail = in.createTypedArrayList(ReqInvoiceToBcSuccessData.CREATOR);
    }

    public SubmitInvoiceToBcParentData() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubmitInvoiceToBcParentData> CREATOR = new Creator<SubmitInvoiceToBcParentData>() {
        @Override
        public SubmitInvoiceToBcParentData createFromParcel(Parcel in) {
            return new SubmitInvoiceToBcParentData(in);
        }

        @Override
        public SubmitInvoiceToBcParentData[] newArray(int size) {
            return new SubmitInvoiceToBcParentData[size];
        }
    };
}
