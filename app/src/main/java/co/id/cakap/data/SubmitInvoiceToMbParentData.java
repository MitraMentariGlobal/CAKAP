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
public class SubmitInvoiceToMbParentData implements Parcelable{
    @SerializedName("detail")
    @Expose
    private List<InvoiceToMbSuccessData> detail;

    protected SubmitInvoiceToMbParentData(Parcel in) {
        detail = in.createTypedArrayList(InvoiceToMbSuccessData.CREATOR);
    }

    public SubmitInvoiceToMbParentData() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(detail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubmitInvoiceToMbParentData> CREATOR = new Creator<SubmitInvoiceToMbParentData>() {
        @Override
        public SubmitInvoiceToMbParentData createFromParcel(Parcel in) {
            return new SubmitInvoiceToMbParentData(in);
        }

        @Override
        public SubmitInvoiceToMbParentData[] newArray(int size) {
            return new SubmitInvoiceToMbParentData[size];
        }
    };
}
