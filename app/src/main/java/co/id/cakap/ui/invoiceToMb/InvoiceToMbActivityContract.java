package co.id.cakap.ui.invoiceToMb;

import java.util.List;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;

public class InvoiceToMbActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopData> resultData, OperationUserStatusData operationUserStatusData);
    }

    public interface UserActionListener{
        void setView(InvoiceToMbActivityContract.View view);
        void getData(String memberId);
    }
}
