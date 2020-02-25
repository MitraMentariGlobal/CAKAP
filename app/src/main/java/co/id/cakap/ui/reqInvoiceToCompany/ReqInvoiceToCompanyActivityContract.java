package co.id.cakap.ui.reqInvoiceToCompany;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.SubmitInvoiceToCompanyData;
import co.id.cakap.network.ApiResponseSubmitInvoiceToCompany;

public class ReqInvoiceToCompanyActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopCompanyData> resultData);
        void setCheckoutValue(List<ItemShopCompanyData> resultData, ItemShopCompanyData itemShopCompanyData, int action);
        void successSubmitData(ApiResponseSubmitInvoiceToCompany apiResponseSubmitInvoiceToCompany);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToCompanyActivityContract.View view);
        void getItemInvoice(String timur);
        void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, String opsi, String idAddress, List<ItemShopCompanyData> resultData);
    }
}
