package co.id.cakap.ui.home;

import co.id.cakap.network.ApiResponseLogin;

public class HomeContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
    }

    public interface UserActionListener{
        void setView(HomeContract.View view);
        void getData();
    }
}
