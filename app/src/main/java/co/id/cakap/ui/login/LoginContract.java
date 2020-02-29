package co.id.cakap.ui.login;

import co.id.cakap.network.ApiResponseLogin;

public class LoginContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse(ApiResponseLogin apiResponseLogin);
    }

    public interface UserActionListener{
        void setView(LoginContract.View view);
        void getNotificationToken(String userId, String password);
        void saveData(ApiResponseLogin apiResponseLogin);
    }
}
