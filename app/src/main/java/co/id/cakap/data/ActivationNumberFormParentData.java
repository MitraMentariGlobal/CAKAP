package co.id.cakap.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ActivationNumberFormParentData {
    @SerializedName("list_form")
    @Expose
    private List<ActivationItemFormData> list_form;
}
