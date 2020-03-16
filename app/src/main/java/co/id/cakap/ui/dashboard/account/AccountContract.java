package co.id.cakap.ui.dashboard.account;

import co.id.cakap.data.ResultDataLogin;

public class AccountContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
        void setLoginData(ResultDataLogin resultDataLogin);
    }

    public interface UserActionListener {
        void getLoginData();
        void getData();
    }
}
