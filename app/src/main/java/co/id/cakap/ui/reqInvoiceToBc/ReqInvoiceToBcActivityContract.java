package co.id.cakap.ui.reqInvoiceToBc;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.SubmitInvoiceToBcData;

public class ReqInvoiceToBcActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopCompanyData> resultData);
        void setCheckoutValue(List<ItemShopCompanyData> resultData, ItemShopCompanyData itemShopCompanyData, int action);
        void successSubmitData(SubmitInvoiceToBcData submitInvoiceToBcData);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToBcActivityContract.View view);
        void getData();
        void getItemInvoice();
        void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopCompanyData> resultData);
    }
}
