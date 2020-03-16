package co.id.cakap.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.cakap.data.BankInfoData;
import co.id.cakap.data.SubmitInvoiceToCompanyData;
import co.id.cakap.data.SubmitInvoiceToMbData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

@Data
public class ApiResponseSubmitInvoiceToCompany {
    @SerializedName("messages")
    private String messages;
    @SerializedName("info")
    private String info;
    @SerializedName("bank")
    private List<BankInfoData> bank;
    @SerializedName("data")
    private SubmitInvoiceToCompanyData data;
}
