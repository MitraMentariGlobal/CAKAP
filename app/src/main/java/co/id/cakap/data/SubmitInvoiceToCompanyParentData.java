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
public class SubmitInvoiceToCompanyParentData implements Parcelable{
    @SerializedName("detail")
    @Expose
    private List<ReqInvoiceToCompanySuccessData> detail;

    protected SubmitInvoiceToCompanyParentData(Parcel in) {
        detail = in.createTypedArrayList(ReqInvoiceToCompanySuccessData.CREATOR);
    }

    public SubmitInvoiceToCompanyParentData() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubmitInvoiceToCompanyParentData> CREATOR = new Creator<SubmitInvoiceToCompanyParentData>() {
        @Override
        public SubmitInvoiceToCompanyParentData createFromParcel(Parcel in) {
            return new SubmitInvoiceToCompanyParentData(in);
        }

        @Override
        public SubmitInvoiceToCompanyParentData[] newArray(int size) {
            return new SubmitInvoiceToCompanyParentData[size];
        }
    };
}
