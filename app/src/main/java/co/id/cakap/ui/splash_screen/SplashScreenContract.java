package co.id.cakap.ui.splash_screen;

import co.id.cakap.network.ApiResponseSession;

public class SplashScreenContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
    }

    public interface UserActionListener{
        void setView(SplashScreenContract.View view);
    }
}
