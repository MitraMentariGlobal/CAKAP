package co.id.cakap.ui.cashbill;

import java.util.List;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.SubmitCashbillData;

public class CashbillActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ItemShopData> resultData, OperationUserStatusData operationUserStatusData);
        void setCheckoutValue(List<ItemShopData> resultData, ItemShopData itemShopData, int action);
        void successSubmitData(SubmitCashbillData submitCashbillData);
    }

    public interface UserActionListener{
        void setView(CashbillActivityContract.View view);
        void getMemberData(String memberId);
        void getItemCashbill(OperationUserStatusData operationUserStatusData);
        void submitData(String pin, String totalHarga, String totalPv, String totalBv, String remark, List<ItemShopData> resultData);
    }
}
