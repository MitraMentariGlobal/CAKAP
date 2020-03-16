package co.id.cakap.ui.reqInvoiceToBc;

import java.util.List;

import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.SubmitInvoiceToBcData;
import co.id.cakap.network.ApiResponseSubmitInvoiceToBc;

public class ReqInvoiceToBcActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopData> resultData);
        void setCheckoutValue(List<ItemShopData> resultData, ItemShopData itemShopData, int action);
        void successSubmitData(ApiResponseSubmitInvoiceToBc apiResponseSubmitInvoiceToBc);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToBcActivityContract.View view);
        void getData();
        void getItemInvoice();
        void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData);
    }
}
