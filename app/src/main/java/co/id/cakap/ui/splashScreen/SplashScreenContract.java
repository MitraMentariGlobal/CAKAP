package co.id.cakap.ui.splashScreen;

import android.content.SharedPreferences;

import co.id.cakap.network.ApiResponseLogin;

public class SplashScreenContract {
    public interface View{
        void initializeData();
        void setErrorResponse(String message);
        void goToHome(String url);
        void goToLogin();
        void finishActivity();
        void showProgressBar();
        void hideProgressBar();

    }

    public interface UserActionListener{
        void saveData(ApiResponseLogin apiResponseLogin);
        void setView(SplashScreenContract.View view);
        void getData();
    }
}
