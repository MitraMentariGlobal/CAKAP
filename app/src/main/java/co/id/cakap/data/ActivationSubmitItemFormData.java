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
public class ActivationSubmitItemFormData {
    private String itemId;
    private String memberId;
    private String numberForm;
    private String qty;

    public ActivationSubmitItemFormData(String itemId, String memberId, String numberForm, String qty) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.numberForm = numberForm;
        this.qty = qty;
    }
}
