package co.id.cakap.ui.invoiceToMb;

import java.util.List;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.SubmitInvoiceToMbData;

public class InvoiceToMbActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopData> resultData, OperationUserStatusData operationUserStatusData);
        void setCheckoutValue(List<ItemShopData> resultData, ItemShopData itemShopData, int action);
        void successSubmitData(SubmitInvoiceToMbData submitInvoiceToMbData);
    }

    public interface UserActionListener{
        void setView(InvoiceToMbActivityContract.View view);
        void getMbData(String mbId);
        void getItemInvoice(OperationUserStatusData operationUserStatusData);
        void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData);
    }
}
