package co.id.cakap.ui.cashbill;

import co.id.cakap.network.ApiResponseLogin;

public class CashbillActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(CashbillActivityContract.View view);
    }
}
