package co.id.cakap.ui.login;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import co.id.cakap.data.ResultData;
import co.id.cakap.network.ApiResponse;
import co.id.cakap.network.ApiResponseLogin;

public class LoginContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
    }

    public interface UserActionListener{
        void setView(LoginContract.View view);
        void getNotificationToken(String userId, String password);
        void saveData(ApiResponseLogin apiResponseLogin);
    }
}
