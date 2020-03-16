package co.id.cakap.ui.cashbill.cashbillSuccess;

import java.util.List;

import co.id.cakap.data.CashbillSuccessData;

public class CashbillSuccessContract {
    public interface View {
        void initializeData();
        void setAdapter(List<CashbillSuccessData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
