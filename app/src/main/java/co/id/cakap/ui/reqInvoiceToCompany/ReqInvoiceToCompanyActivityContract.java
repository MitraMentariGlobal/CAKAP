package co.id.cakap.ui.reqInvoiceToCompany;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;

public class ReqInvoiceToCompanyActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopCompanyData> resultData);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToCompanyActivityContract.View view);
        void getData();
    }
}
