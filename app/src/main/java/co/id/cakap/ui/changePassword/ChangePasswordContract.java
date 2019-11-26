package co.id.cakap.ui.changePassword;

public class ChangePasswordContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setSuccessResponse();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(ChangePasswordContract.View view);
        void changeData(String oldPassword, String newPassword, String retypeNewPassword, String pin);
    }
}
