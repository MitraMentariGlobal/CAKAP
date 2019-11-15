package co.id.cakap.ui.reqInvoiceToBc;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;

public class ReqInvoiceToBcActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopCompanyData> resultData);
        void setCheckoutValue(List<ItemShopCompanyData> resultData, ItemShopCompanyData itemShopCompanyData, int action);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToBcActivityContract.View view);
        void getData();
    }
}
