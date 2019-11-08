package co.id.cakap.ui.splashScreen;

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
        void setView(SplashScreenContract.View view);
        void getData();
    }
}
