package co.id.cakap.ui.homeWebView;

public class HomeWebViewContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
    }

    public interface UserActionListener{
        void setView(HomeWebViewContract.View view);
        void getData();
    }
}
