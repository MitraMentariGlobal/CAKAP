package co.id.cakap.ui.cashbill;

import java.util.List;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;

public class CashbillActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopData> resultData, OperationUserStatusData operationUserStatusData);
    }

    public interface UserActionListener{
        void setView(CashbillActivityContract.View view);
        void getData(String memberId);
    }
}
